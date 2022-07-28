package com.telecom.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.beans.ActiveNumber;
import com.telecom.beans.ActivePlan;
import com.telecom.beans.Device;
import com.telecom.beans.Plan;
import com.telecom.beans.User;
import com.telecom.jwt.JwtTokenUtil;
import com.telecom.models.AddLineRequest;
import com.telecom.models.AddPlanRequest;
import com.telecom.models.AssignPlanRequest;
import com.telecom.models.AuthRequest;
import com.telecom.models.AuthResponse;
import com.telecom.models.RegisterRequest;
import com.telecom.services.ActiveNumberService;
import com.telecom.services.ActivePlanService;
import com.telecom.services.DeviceService;
import com.telecom.services.PlanService;
import com.telecom.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/user")
@Tag(name = "User")
@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Successful"),
	@ApiResponse(responseCode = "201", description = "Created"),
	@ApiResponse(responseCode = "401", description = "Unauthorized")
})
public class UserController {

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private UserService userService;

	@Autowired
	private ActivePlanService activePlanService;

	@Autowired
	private PlanService planService;

	@Autowired
	private ActiveNumberService activeNumberService;

	@Autowired
	private DeviceService deviceService;

	@Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtTokenUtil jwtUtil;

	@GetMapping("/email/{email}")
	@Operation(summary = "Find User by email", description = "Return User with matching email")
	public ResponseEntity<Optional<User>> findByEmail(@PathVariable(value="email") String email) {
		return new ResponseEntity<Optional<User>>(userService.findByEmail(email), HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	@Operation(summary = "Find User by id", description = "Return User with matching id", security = 
		@SecurityRequirement(name = "JWT Authentication"))
	public ResponseEntity<Optional<User>> findById(@PathVariable(value="id") int id) {
		return new ResponseEntity<Optional<User>>(userService.findById(id), HttpStatus.OK);
	}

	@GetMapping("/")
	@Operation(summary = "Find all Users", description = "Return all Users", security = 
		@SecurityRequirement(name = "JWT Authentication"))
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}

	@PostMapping("/register")
	@Operation(summary = "Add a new User", description = "Register a new User")
	public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
		registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		User user = new User(0, registerRequest.getEmail(), registerRequest.getFirstName(), registerRequest.getLastName(), registerRequest.getPassword(), new HashSet<ActivePlan>());
		return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	@Operation(summary = "Login a User", description = "Login a User and get JWT Token")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest) {
        try {
            Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authRequest.getEmail(), authRequest.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user, accessToken);

            return new ResponseEntity<AuthResponse>(response, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

	@PostMapping("/addplan")
	@Operation(summary = "Add an Active Plan", description = "Add a Plan to the User's Active Plans", security = 
		@SecurityRequirement(name = "JWT Authentication"))
	public ResponseEntity<User> addPlan(@RequestBody AddPlanRequest addPlanRequest) {
		User user = userService.findById(addPlanRequest.getUserId()).get();
		Plan plan = planService.findById(addPlanRequest.getPlanId()).get();
		ActivePlan activePlan = activePlanService.save(new ActivePlan(0, user, plan, new HashSet<ActiveNumber>()));
		user.addActivePlan(activePlan);
		return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
	}

	@PostMapping("/assignplan")
	@Operation(summary = "Assign a new Plan to an Active Plan", description = "Assign a new Plan to a specified Active Plan", security = 
		@SecurityRequirement(name = "JWT Authentication"))
	public ResponseEntity<ActivePlan> assignPlan(@RequestBody AssignPlanRequest assignPlanRequest) {
		Plan plan = planService.findById(assignPlanRequest.getPlanId()).get();
		ActivePlan activePlan = activePlanService.findById(assignPlanRequest.getActivePlanId()).get();
		activePlan.setPlan(plan);
		return new ResponseEntity<ActivePlan>(activePlanService.save(activePlan), HttpStatus.CREATED);
	}

	@PostMapping("/addline")
	@Operation(summary = "Add an Active Number to a specified Active Plan", description = "Add a Line to a User's Active Plan", security = 
		@SecurityRequirement(name = "JWT Authentication"))
	public ResponseEntity<User> addLine(@RequestBody AddLineRequest addLineRequest) {
		User user = userService.findById(addLineRequest.getUserId()).get();
		ActivePlan activePlan = activePlanService.findById(addLineRequest.getActivePlanId()).get();
		List<Device> devices = deviceService.findAll();
		activeNumberService.save(new ActiveNumber(0, addLineRequest.getPhoneNumber(), false, devices.get(0), activePlan));
		return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
	}

	@DeleteMapping("/removeline/{activeNumberId}")
	@Operation(summary = "Delete an Active Number", description = "Delete a specified Active Number", security = 
		@SecurityRequirement(name = "JWT Authentication"))
	public ResponseEntity<Void> removeLine(@PathVariable(value = "activeNumberId") int activeNumberId) {
		activeNumberService.deleteById(activeNumberId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/removeplan/{activePlanId}")
	@Operation(summary = "Delete an Active Plan", description = "Delete a specified Active Plan", security = 
		@SecurityRequirement(name = "JWT Authentication"))
	public ResponseEntity<Void> removePlan(@PathVariable(value = "activePlanId") int activePlanId) {
		activePlanService.deleteById(activePlanId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete User by id", description = "Delete User with matching id", security = 
		@SecurityRequirement(name = "JWT Authentication"))
	public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
		userService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
