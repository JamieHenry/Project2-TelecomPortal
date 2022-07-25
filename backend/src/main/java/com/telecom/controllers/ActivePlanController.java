package com.telecom.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.beans.ActivePlan;
import com.telecom.services.ActivePlanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/activeplan")
@Tag(name = "Active Plan")
@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Successful"),
	@ApiResponse(responseCode = "401", description = "Unauthorized")
})
@SecurityRequirement(name = "JWT Authentication")
public class ActivePlanController {

	@Autowired
	private ActivePlanService service;
	
	@GetMapping("/userid/{userId}")
	@Operation(summary = "Find all Active Plans by planId", description = "Return all Active Plans with matching planId")
	public ResponseEntity<List<ActivePlan>> findByUserId(@PathVariable(value="userId") int userId) {
		return new ResponseEntity<List<ActivePlan>>(service.findByUserId(userId), HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
    @Operation(summary = "Find Active Plan by id", description = "Return Active Plan with matching id")
	public ResponseEntity<Optional<ActivePlan>> findById(@PathVariable(value="id") int id) {
		return new ResponseEntity<Optional<ActivePlan>>(service.findById(id), HttpStatus.OK);
	}

	@GetMapping("/")
    @Operation(summary = "Find all Active Plans", description = "Return all Active Plans")
	public ResponseEntity<List<ActivePlan>> findAll() {
		return new ResponseEntity<List<ActivePlan>>(service.findAll(), HttpStatus.OK);
	}

	@PostMapping("/")
    @Operation(summary = "Save a new Active Plan", description = "Save and return a new Active Plan")
	public ResponseEntity<ActivePlan> save(@RequestBody ActivePlan activePlan) {
		return new ResponseEntity<ActivePlan>(service.save(activePlan), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
    @Operation(summary = "Delete Active Plan by id", description = "Delete Active Plan with matching id")
	public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
		service.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
