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

import com.telecom.beans.ActivePlan;
import com.telecom.services.ActivePlanService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/activeplan")
public class ActivePlanController {

	@Autowired
	private ActivePlanService service;
	
	@GetMapping("/userid/{userId}")
	public ResponseEntity<List<ActivePlan>> findByUserId(@PathVariable(value="userId") int userId) {
		return new ResponseEntity<List<ActivePlan>>(service.findByUserId(userId), HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Optional<ActivePlan>> findById(@PathVariable(value="id") int id) {
		return new ResponseEntity<Optional<ActivePlan>>(service.findById(id), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<ActivePlan>> findAll() {
		return new ResponseEntity<List<ActivePlan>>(service.findAll(), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<ActivePlan> save(@RequestBody ActivePlan activePlan) {
		return new ResponseEntity<ActivePlan>(service.save(activePlan), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
		service.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
