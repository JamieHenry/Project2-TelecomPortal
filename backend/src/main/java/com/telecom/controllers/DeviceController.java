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

import com.telecom.beans.Device;
import com.telecom.models.MakeAndModelRequest;
import com.telecom.services.DeviceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/device")
@Tag(name = "Device")
@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Successful"),
	@ApiResponse(responseCode = "401", description = "Unauthorized")
})
@SecurityRequirement(name = "JWT Authentication")
public class DeviceController {
    
    @Autowired
    private DeviceService service;

    @PostMapping("/makemodel")
    @Operation(summary = "Find Device by make and model", description = "Return Device with matching make and model")
    public ResponseEntity<Optional<Device>> findByModel(@RequestBody MakeAndModelRequest makeAndModelRequest) {
        return new ResponseEntity<Optional<Device>>(service.findByMakeAndModel(makeAndModelRequest.getMake(), makeAndModelRequest.getModel()), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Find Device by id", description = "Return Device with matching id")
    public ResponseEntity<Optional<Device>> findById(@PathVariable(value="id") int id) {
        return new ResponseEntity<Optional<Device>>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    @Operation(summary = "Find all Devices", description = "Return all Devices")
    public ResponseEntity<List<Device>> findAll() {
        return new ResponseEntity<List<Device>>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "Save a new Device", description = "Save and return a new Device")
    public ResponseEntity<Device> save(@RequestBody Device device) {
        return new ResponseEntity<Device>(service.save(device), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Device by id", description = "Delete Device with matching id")
    public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
        service.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
