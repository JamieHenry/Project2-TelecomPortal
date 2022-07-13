package com.telecom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.beans.ActiveNumber;
import com.telecom.data.ActiveNumberRepository;

@Service
public class ActiveNumberService {

	@Autowired
	private ActiveNumberRepository repository;
	
	public ActiveNumber save(ActiveNumber activeNumber) {
		return repository.save(activeNumber);
	}
	
	public List<ActiveNumber> findByUserId(int userId) {
		return repository.findByUserId(userId);
	}
	
	public List<ActiveNumber> findByPlanId(int planId) {
		return repository.findByPlanId(planId);
	}
	
	public ActiveNumber findById(int id) {
		return repository.getReferenceById(id);
	}
	
	public List<ActiveNumber> findAll() {
		return repository.findAll();
	}
	
	public void delete(ActiveNumber activeNumber) {
		repository.delete(activeNumber);
	}
}
