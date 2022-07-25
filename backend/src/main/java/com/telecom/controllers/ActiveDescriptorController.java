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

import com.telecom.beans.ActiveDescriptor;
import com.telecom.services.ActiveDescriptorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/activedescriptor")
@Tag(name = "Active Descriptor")
@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Successful"),
	@ApiResponse(responseCode = "401", description = "Unauthorized")
})
@SecurityRequirement(name = "JWT Authentication")
public class ActiveDescriptorController {
    
    @Autowired
    private ActiveDescriptorService service;

    @GetMapping("/planid/{planId}")
    @Operation(summary = "Find Active Descriptors by planId", description = "Return all Active Descriptors with matching planId")
    public ResponseEntity<List<ActiveDescriptor>> findByPlanId(@PathVariable(value="planId") int planId) {
        return new ResponseEntity<List<ActiveDescriptor>>(service.findByPlanId(planId), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Find Active Descriptor by id", description = "Return Active Descriptor with matching id")
    public ResponseEntity<Optional<ActiveDescriptor>> findById(@PathVariable(value="id") int id) {
        return new ResponseEntity<Optional<ActiveDescriptor>>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    @Operation(summary = "Find all Active Descriptors", description = "Return all Active Descriptors")
    public ResponseEntity<List<ActiveDescriptor>> findAll() {
        return new ResponseEntity<List<ActiveDescriptor>>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "Save a new Active Descriptor", description = "Save and return a new Active Descriptor")
    public ResponseEntity<ActiveDescriptor> save(@RequestBody ActiveDescriptor activeDescriptor) {
        return new ResponseEntity<ActiveDescriptor>(service.save(activeDescriptor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Active Descriptor by id", description = "Delete Active Descriptor with matching id")
	public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
		service.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
