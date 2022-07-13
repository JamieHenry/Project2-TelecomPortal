package com.telecom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.beans.ActivePlan;
import com.telecom.services.ActivePlanService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ActivePlanController {

	@Autowired
	private ActivePlanService service;
	
	@GetMapping("/api/activeplan")
	public ResponseEntity<List<ActivePlan>> findAll() {
		return new ResponseEntity<List<ActivePlan>>(service.findAll(), HttpStatus.OK);
	}
}
