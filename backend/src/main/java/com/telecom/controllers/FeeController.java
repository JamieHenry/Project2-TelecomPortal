package com.telecom.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.beans.Fee;
import com.telecom.services.FeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/fee")
@Tag(name = "Fee")
@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Successful"),
	@ApiResponse(responseCode = "401", description = "Unauthorized")
})
@SecurityRequirement(name = "JWT Authentication")
public class FeeController {
    
    @Autowired
    private FeeService service;

    @GetMapping("/id/{id}")
    @Operation(summary = "Find Fee by id", description = "Return Fee with matching id")
    public ResponseEntity<Optional<Fee>> findById(@PathVariable(value="id") int id) {
        return new ResponseEntity<Optional<Fee>>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    @Operation(summary = "Find all Fees", description = "Return all Fees")
    public ResponseEntity<List<Fee>> findAll() {
        return new ResponseEntity<List<Fee>>(service.findAll(), HttpStatus.OK);
    }
    
    @PostMapping("/")
    @Operation(summary = "Save a new Fee", description = "Save and return a new Fee")
    public ResponseEntity<Fee> save(@RequestBody Fee fee) {
        return new ResponseEntity<Fee>(service.save(fee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Fee by id", description = "Delete Fee with matching id")
    public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
        service.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
