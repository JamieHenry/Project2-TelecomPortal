package com.telecom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.beans.Descriptor;
import com.telecom.data.DescriptorRepository;

@Service
public class DescriptorService {

	@Autowired
	private DescriptorRepository repository;
	
	public Descriptor save(Descriptor descriptor) {
		return repository.save(descriptor);
	}
	
	public Optional<Descriptor> findById(int id) {
		return repository.findById(id);
	}
	
	public List<Descriptor> findAll() {
		return repository.findAll();
	}
	
	public void deleteById(int id) {
		repository.deleteById(id);
	}
}
