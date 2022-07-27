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

import com.telecom.beans.Descriptor;
import com.telecom.services.DescriptorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/descriptor")
@Tag(name = "Descriptor")
@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Successful"),
    @ApiResponse(responseCode = "201", description = "Created"),
	@ApiResponse(responseCode = "401", description = "Unauthorized")
})
@SecurityRequirement(name = "JWT Authentication")
public class DescriptorController {
    
    @Autowired
    private DescriptorService service;

    @GetMapping("/id/{id}")
    @Operation(summary = "Find Descriptor by id", description = "Return Descriptor with matching id")
    public ResponseEntity<Optional<Descriptor>> findById(@PathVariable(value="id") int id) {
        return new ResponseEntity<Optional<Descriptor>>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    @Operation(summary = "Find all Descriptors", description = "Return all Descriptors")
    public ResponseEntity<List<Descriptor>> findAll() {
        return new ResponseEntity<List<Descriptor>>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "Save a new Descriptor", description = "Save and return a new Descriptor")
    public ResponseEntity<Descriptor> save(@RequestBody Descriptor descriptor) {
        return new ResponseEntity<Descriptor>(service.save(descriptor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Descriptor by id", description = "Delete Descriptor with matching id")
    public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
        service.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
