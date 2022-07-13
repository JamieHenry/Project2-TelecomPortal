package com.telecom.controllers;

import java.util.Base64;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.beans.User;
import com.telecom.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService service;
	
	PBEKeySpec pbeKeySpec;
	SecretKeyFactory skf;
	
	@PostMapping("/api/user")
	public ResponseEntity<User> save(@RequestBody User user) {
		// hash password and set
		byte[] hash;
		pbeKeySpec = new PBEKeySpec(user.getPassword().toCharArray(), user.getEmail().getBytes(), 10, 512);
		try {
			skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			hash = skf.generateSecret(pbeKeySpec).getEncoded();
			user.setPassword(Base64.getMimeEncoder().encodeToString(hash));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// if login, first/last name will be null
		if (user.getFirstName() == null) {
			return new ResponseEntity<>(service.login(user.getEmail(), user.getPassword()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(service.register(user), HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/api/user")
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<List<User>>(service.findAll(), HttpStatus.OK);
	}
}
