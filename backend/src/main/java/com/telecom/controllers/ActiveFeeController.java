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

import com.telecom.beans.ActiveFee;
import com.telecom.services.ActiveFeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/activefee")
@Tag(name = "Active Fee")
@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Successful"),
	@ApiResponse(responseCode = "401", description = "Unauthorized")
})
@SecurityRequirement(name = "JWT Authentication")
public class ActiveFeeController {
    
    @Autowired
    private ActiveFeeService service;

    @GetMapping("/planid/{planId}")
    @Operation(summary = "Find all Active Fees by planId", description = "Return all Active Fees with matching planId")
    public ResponseEntity<List<ActiveFee>> findByPlanId(@PathVariable(value="planId") int planId) {
        return new ResponseEntity<List<ActiveFee>>(service.findByPlanId(planId), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Find Active Fee by id", description = "Return Active Fee with matching id")
    public ResponseEntity<Optional<ActiveFee>> findById(@PathVariable(value="id") int id) {
        return new ResponseEntity<Optional<ActiveFee>>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    @Operation(summary = "Find all Active Fees", description = "Return all Active Fees")
    public ResponseEntity<List<ActiveFee>> findAll() {
        return new ResponseEntity<List<ActiveFee>>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "Save a new Active Fee", description = "Save and return a new Active Fee")
    public ResponseEntity<ActiveFee> save(@RequestBody ActiveFee activeFee) {
        return new ResponseEntity<ActiveFee>(service.save(activeFee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Active Fee by id", description = "Delete Active Fee with matching id")
	public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
		service.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
