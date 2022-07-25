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

import com.telecom.beans.ActiveNumber;
import com.telecom.services.ActiveNumberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/activenumber")
@Tag(name = "Active Number")
@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Successful"),
	@ApiResponse(responseCode = "401", description = "Unauthorized")
})
@SecurityRequirement(name = "JWT Authentication")
public class ActiveNumberController {
    
    @Autowired
    private ActiveNumberService service;

    @GetMapping("/userid/{userId}")
    @Operation(summary = "Find all Active Numbers by userId", description = "Return all Active Numbers with matching userid")
    public ResponseEntity<List<ActiveNumber>> findByUserId(@PathVariable(value="userId") int userId) {
        return new ResponseEntity<List<ActiveNumber>>(service.findByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/activeplanid/{activePlanId}")
    @Operation(summary = "Find all Active Numbers by activePlanId", description = "Return all Active Numbers with matching activePlanId")
    public ResponseEntity<List<ActiveNumber>> findByActivePlanId(@PathVariable(value="activePlanId") int activePlanId) {
        return new ResponseEntity<List<ActiveNumber>>(service.findByActivePlanId(activePlanId), HttpStatus.OK);
    }

    @GetMapping("/phonenumber/{phoneNumber}")
    @Operation(summary = "Find Active Number by phoneNumber", description = "Return Active Number with matching phoneNumber")
    public ResponseEntity<Optional<ActiveNumber>> findByPhoneNumber(@PathVariable(value="phoneNumber") String phoneNumber) {
        return new ResponseEntity<Optional<ActiveNumber>>(service.findByPhoneNumber(phoneNumber), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Find Active Number by id", description = "Return Active Number with matching id")
    public ResponseEntity<Optional<ActiveNumber>> findById(@PathVariable(value="id") int id) {
        return new ResponseEntity<Optional<ActiveNumber>>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    @Operation(summary = "Find all Active Numbers", description = "Return all Active Numbers")
    public ResponseEntity<List<ActiveNumber>> findAll() {
        return new ResponseEntity<List<ActiveNumber>>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "Save a new Active Number", description = "Save and return a new Active Number")
    public ResponseEntity<ActiveNumber> save(@RequestBody ActiveNumber activeNumber) {
        return new ResponseEntity<ActiveNumber>(service.save(activeNumber), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Active Number by id", description = "Delete Active Number with matching id")
	public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
		service.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
