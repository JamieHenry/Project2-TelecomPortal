package com.telecom.services;

import java.util.List;

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
	
	public ActiveFee findById(int id) {
		return repository.getReferenceById(id);
	}
	
	public List<ActiveFee> findAll() {
		return repository.findAll();
	}
	
	public void delete(ActiveFee activeFee) {
		repository.delete(activeFee);
	}
}
