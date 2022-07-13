package com.telecom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.beans.ActiveDescriptor;
import com.telecom.data.ActiveDescriptorRepository;

@Service
public class ActiveDescriptorService {

	@Autowired
	private ActiveDescriptorRepository repository;
	
	public ActiveDescriptor save(ActiveDescriptor activeDescriptor) {
		return repository.save(activeDescriptor);
	}
	
	public List<ActiveDescriptor> findByPlanId(int planId) {
		return repository.findByPlanId(planId);
	}
	
	public ActiveDescriptor findById(int id) {
		return repository.getReferenceById(id);
	}
	
	public List<ActiveDescriptor> findAll() {
		return repository.findAll();
	}
	
	public void delete(ActiveDescriptor activeDescriptor) {
		repository.delete(activeDescriptor);
	}
}
