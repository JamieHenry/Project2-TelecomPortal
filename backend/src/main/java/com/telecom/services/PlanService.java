package com.telecom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.beans.Plan;
import com.telecom.data.PlanRepository;

@Service
public class PlanService {

	@Autowired
	private PlanRepository repository;
	
	public Plan save(Plan plan) {
		return repository.save(plan);
	}
	
	public Plan findById(int id) {
		return repository.getReferenceById(id);
	}
	
	public List<Plan> findAll(Plan plan) {
		return repository.findAll();
	}
	
	public void delete(Plan plan) {
		repository.delete(plan);
	}
}
