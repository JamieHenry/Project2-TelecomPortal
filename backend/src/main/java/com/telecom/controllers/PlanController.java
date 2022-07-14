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

import com.telecom.beans.Plan;
import com.telecom.services.PlanService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/plan")
public class PlanController {
    
    @Autowired
    private PlanService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Plan>> findById(@PathVariable(value="id") int id) {
        return new ResponseEntity<Optional<Plan>>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Plan>> findAll() {
        return new ResponseEntity<List<Plan>>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Plan> save(@RequestBody Plan plan) {
        return new ResponseEntity<Plan>(service.save(plan), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
        service.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
