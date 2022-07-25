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
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.telecom.beans.ActiveDeviceDescriptor;
import com.telecom.services.ActiveDeviceDescriptorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestControllerAdvice
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/activedevicedescriptor")
@Tag(name = "Active Device Descriptor")
@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Successful"),
	@ApiResponse(responseCode = "401", description = "Unauthorized")
})
@SecurityRequirement(name = "JWT Authentication")
public class ActiveDeviceDescriptorController {
    
    @Autowired
    private ActiveDeviceDescriptorService service;

    @GetMapping("/deviceid/{deviceId}")
    @Operation(summary = "Find all Active Device Descriptors by deviceId", description = "Return all Active Device Descriptors with matching deviceId")
    public ResponseEntity<List<ActiveDeviceDescriptor>> findByPlanId(@PathVariable(value="deviceId") int deviceId) {
        return new ResponseEntity<List<ActiveDeviceDescriptor>>(service.findByDeviceId(deviceId), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Find Active Device Descriptor by id", description = "Return Active Device Descriptor with matching id")
    public ResponseEntity<Optional<ActiveDeviceDescriptor>> findById(@PathVariable(value="id") int id) {
        return new ResponseEntity<Optional<ActiveDeviceDescriptor>>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    @Operation(summary = "Find all Active Device Descriptors", description = "Return all Active Device Descriptors")
    public ResponseEntity<List<ActiveDeviceDescriptor>> findAll() {
        return new ResponseEntity<List<ActiveDeviceDescriptor>>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "Save a new Active Device Descriptor", description = "Save and return a new Active Device Descriptor")
    public ResponseEntity<ActiveDeviceDescriptor> save(@RequestBody ActiveDeviceDescriptor activeDeviceDescriptor) {
        return new ResponseEntity<ActiveDeviceDescriptor>(service.save(activeDeviceDescriptor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Active Device Descriptor by id", description = "Delete Active Device Descriptor with matching id")
	public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
		service.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
