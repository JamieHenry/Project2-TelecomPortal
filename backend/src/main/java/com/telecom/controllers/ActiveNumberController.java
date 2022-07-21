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

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/activenumber")
public class ActiveNumberController {
    
    @Autowired
    private ActiveNumberService service;

    @GetMapping("/userid/{userId}")
    public ResponseEntity<List<ActiveNumber>> findByUserId(@PathVariable(value="userId") int userId) {
        return new ResponseEntity<List<ActiveNumber>>(service.findByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/activeplanid/{activePlanId}")
    public ResponseEntity<List<ActiveNumber>> findByActivePlanId(@PathVariable(value="activePlanId") int activePlanId) {
        return new ResponseEntity<List<ActiveNumber>>(service.findByActivePlanId(activePlanId), HttpStatus.OK);
    }

    @GetMapping("/phonenumber/{phoneNumber}")
    public ResponseEntity<Optional<ActiveNumber>> findById(@PathVariable(value="phoneNumber") String phoneNumber) {
        return new ResponseEntity<Optional<ActiveNumber>>(service.findByPhoneNumber(phoneNumber), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ActiveNumber>> findAll() {
        return new ResponseEntity<List<ActiveNumber>>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ActiveNumber> save(@RequestBody ActiveNumber activeNumber) {
        return new ResponseEntity<ActiveNumber>(service.save(activeNumber), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
		service.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
