package com.telecom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.beans.Fee;
import com.telecom.data.FeeRepository;

@Service
public class FeeService {

	@Autowired
	private FeeRepository repository;
	
	public Fee save(Fee fee) {
		return repository.save(fee);
	}
	
	public Optional<Fee> findById(int id) {
		return repository.findById(id);
	}
	
	public List<Fee> findAll() {
		return repository.findAll();
	}
	
	public void deleteById(int id) {
		repository.deleteById(id);
	}
}
