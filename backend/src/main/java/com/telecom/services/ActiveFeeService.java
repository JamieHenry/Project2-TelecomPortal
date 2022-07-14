package com.telecom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.beans.ActiveFee;
import com.telecom.data.ActiveFeeRepository;

@Service
public class ActiveFeeService {

	@Autowired
	private ActiveFeeRepository repository;
	
	public ActiveFee save(ActiveFee activeFee) {
		return repository.save(activeFee);
	}
	
	public List<ActiveFee> findByPlanId(int planId) {
		return repository.findByPlanId(planId);
	}
	
	public Optional<ActiveFee> findById(int id) {
		return repository.findById(id);
	}
	
	public List<ActiveFee> findAll() {
		return repository.findAll();
	}
	
	public void deleteById(int id) {
		repository.deleteById(id);
	}
}
