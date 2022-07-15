package com.telecom.controllers;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

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

import com.telecom.beans.User;
import com.telecom.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService service;
	
	PBEKeySpec pbeKeySpec;
	SecretKeyFactory skf;

	private String hashPassword(String email, String password) {
		String hashedPassword = "";
		byte[] hash;
		pbeKeySpec = new PBEKeySpec(password.toCharArray(), email.getBytes(), 10, 512);
		
		try {
			skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			hash = skf.generateSecret(pbeKeySpec).getEncoded();
			hashedPassword = Base64.getMimeEncoder().encodeToString(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hashedPassword;
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<Optional<User>> findByEmail(@PathVariable(value="email") String email) {
		return new ResponseEntity<Optional<User>>(service.findByEmail(email), HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Optional<User>> findById(@PathVariable(value="id") int id) {
		return new ResponseEntity<Optional<User>>(service.findById(id), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<List<User>>(service.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Optional<User>> login(@RequestBody Map<String, String> body) {
		body.replace("password", hashPassword(body.get("email"), body.get("password")));
		return new ResponseEntity<Optional<User>>(service.login(body.get("email"), body.get("password")), HttpStatus.OK);
		
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		user.setPassword(hashPassword(user.getEmail(), user.getPassword()));
		return new ResponseEntity<>(service.register(user), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
		service.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
