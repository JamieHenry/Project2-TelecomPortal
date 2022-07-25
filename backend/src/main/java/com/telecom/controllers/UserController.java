package com.telecom.controllers;

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

import com.telecom.beans.User;
import com.telecom.jwt.JwtTokenUtil;
import com.telecom.models.AuthRequest;
import com.telecom.models.AuthResponse;
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
	@ApiResponse(responseCode = "401", description = "Unauthorized")
})
public class UserController {

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private UserService service;

	@Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtTokenUtil jwtUtil;

	@GetMapping("/email/{email}")
	@Operation(summary = "Find User by email", description = "Return User with matching email")
	public ResponseEntity<Optional<User>> findByEmail(@PathVariable(value="email") String email) {
		return new ResponseEntity<Optional<User>>(service.findByEmail(email), HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	@Operation(summary = "Find User by email", description = "Return User with matching email", security = 
		@SecurityRequirement(name = "JWT Authentication"))
	public ResponseEntity<Optional<User>> findById(@PathVariable(value="id") int id) {
		return new ResponseEntity<Optional<User>>(service.findById(id), HttpStatus.OK);
	}

	@GetMapping("/")
	@Operation(summary = "Find all Users", description = "Return all Users", security = 
		@SecurityRequirement(name = "JWT Authentication"))
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<List<User>>(service.findAll(), HttpStatus.OK);
	}

	@PostMapping("/register")
	@Operation(summary = "Add a new User", description = "Register a new User")
	public ResponseEntity<User> register(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return new ResponseEntity<>(service.register(user), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	@Operation(summary = "Login a User", description = "Login a User and get JWT Token")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest authRequest) {
        try {
            Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authRequest.getEmail(), authRequest.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user, accessToken);

            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete User by id", description = "Delete User with matching id", security = 
		@SecurityRequirement(name = "JWT Authentication"))
	public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
		service.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
