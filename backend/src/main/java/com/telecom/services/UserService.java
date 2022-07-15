package com.telecom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.beans.User;
import com.telecom.data.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User register(User user) {
		return repository.save(user);
	}
	
	public Optional<User> login(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}

	public Optional<User> findByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	public Optional<User> findById(int id) {
		return repository.findById(id);
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public void deleteById(int id) {
		repository.deleteById(id);
	}
}
