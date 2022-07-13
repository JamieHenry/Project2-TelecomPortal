package com.telecom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.beans.ActivePlan;
import com.telecom.data.ActivePlanRepository;

@Service
public class ActivePlanService {

	@Autowired
	private ActivePlanRepository repository;
	
	public ActivePlan save(ActivePlan activePlan) {
		return repository.save(activePlan);
	}
	
	public List<ActivePlan> findByUserId(int userId) {
		return repository.findByUserId(userId);
	}
	
	public ActivePlan findById(int id) {
		return repository.getReferenceById(id);
	}
	
	public List<ActivePlan> findAll() {
		return repository.findAll();
	}
	
	public void delete(ActivePlan activePlan) {
		repository.delete(activePlan);
	}
}
