package com.telecom.services;

import java.util.List;

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
	
	public Descriptor findById(int id) {
		return repository.getReferenceById(id);
	}
	
	public List<Descriptor> findAll() {
		return repository.findAll();
	}
	
	public void delete(Descriptor descriptor) {
		repository.delete(descriptor);
	}
}
