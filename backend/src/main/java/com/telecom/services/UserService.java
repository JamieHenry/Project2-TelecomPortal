package com.telecom.services;

import java.util.List;

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
	
	public User login(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}
	
	public User findById(int id) {
		return repository.getReferenceById(id);
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public void delete(User user) {
		repository.delete(user);
	}
}
