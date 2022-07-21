package com.telecom.services;

import java.util.List;
import java.util.Optional;

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
	
	public List<ActiveNumber> findByActivePlanId(int activePlanId) {
		return repository.findByActivePlanId(activePlanId);
	}
	
	public Optional<ActiveNumber> findByPhoneNumber(String phoneNumber) {
		return repository.findByPhoneNumber(phoneNumber);
	}
	
	public List<ActiveNumber> findAll() {
		return repository.findAll();
	}
	
	public void deleteById(int id) {
		repository.deleteById(id);
	}
}
