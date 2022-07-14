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

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/activefee")
public class ActiveFeeController {
    
    @Autowired
    private ActiveFeeService service;

    @GetMapping("/planid/{planId}")
    public ResponseEntity<List<ActiveFee>> findByPlanId(@PathVariable(value="planId") int planId) {
        return new ResponseEntity<List<ActiveFee>>(service.findByPlanId(planId), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<ActiveFee>> findById(@PathVariable(value="id") int id) {
        return new ResponseEntity<Optional<ActiveFee>>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ActiveFee>> findAll() {
        return new ResponseEntity<List<ActiveFee>>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ActiveFee> save(@RequestBody ActiveFee activeFee) {
        return new ResponseEntity<ActiveFee>(service.save(activeFee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
		service.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
