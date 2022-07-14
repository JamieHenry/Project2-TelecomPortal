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

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/activedescriptor")
public class ActiveDescriptorController {
    
    @Autowired
    private ActiveDescriptorService service;

    @GetMapping("/planid/{planId}")
    public ResponseEntity<List<ActiveDescriptor>> findByPlanId(@PathVariable(value="planId") int planId) {
        return new ResponseEntity<List<ActiveDescriptor>>(service.findByPlanId(planId), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<ActiveDescriptor>> findById(@PathVariable(value="id") int id) {
        return new ResponseEntity<Optional<ActiveDescriptor>>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ActiveDescriptor>> findAll() {
        return new ResponseEntity<List<ActiveDescriptor>>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ActiveDescriptor> save(@RequestBody ActiveDescriptor activeDescriptor) {
        return new ResponseEntity<ActiveDescriptor>(service.save(activeDescriptor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
		service.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
