package com.telecom.controllers;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
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

import com.telecom.beans.Plan;
import com.telecom.services.PlanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/plan")
@Tag(name = "Plan")
@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Successful"),
    @ApiResponse(responseCode = "201", description = "Created"),
	@ApiResponse(responseCode = "401", description = "Unauthorized")
})
@SecurityRequirement(name = "JWT Authentication")
public class PlanController {
    
    private static final Logger logger = Logger.getLogger(PlanController.class);

    @Autowired
    private PlanService service;

    @GetMapping("/id/{id}")
    @Operation(summary = "Find Plan by id", description = "Return Plan with matching id")
    public ResponseEntity<Optional<Plan>> findById(@PathVariable(value="id") int id) {
        logger.info("Find Plan by id: " + id);
        return new ResponseEntity<Optional<Plan>>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    @Operation(summary = "Find all Plans", description = "Return all Plans")
    public ResponseEntity<List<Plan>> findAll() {
        logger.info("Find all Plans");
        return new ResponseEntity<List<Plan>>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "Save a new Plan", description = "Save and return a new Plan")
    public ResponseEntity<Plan> save(@RequestBody Plan plan) {
        logger.info("Save new Plan: " + plan);
        return new ResponseEntity<Plan>(service.save(plan), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Plan by id", description = "Delete Plan with matching id")
    public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
        logger.info("Remove Plan with id: " + id);
        service.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
