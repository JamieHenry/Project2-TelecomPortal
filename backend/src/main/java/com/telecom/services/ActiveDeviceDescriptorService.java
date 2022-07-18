package com.telecom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.beans.ActiveDeviceDescriptor;
import com.telecom.data.ActiveDeviceDescriptorRepository;

@Service
public class ActiveDeviceDescriptorService {
    
    @Autowired
	private ActiveDeviceDescriptorRepository repository;
	
	public ActiveDeviceDescriptor save(ActiveDeviceDescriptor activeDeviceDescriptor) {
		return repository.save(activeDeviceDescriptor);
	}
	
	public List<ActiveDeviceDescriptor> findByDeviceId(int deviceId) {
		return repository.findByDeviceId(deviceId);
	}
	
	public Optional<ActiveDeviceDescriptor> findById(int id) {
		return repository.findById(id);
	}
	
	public List<ActiveDeviceDescriptor> findAll() {
		return repository.findAll();
	}
	
	public void deleteById(int id) {
		repository.deleteById(id);
	}
}
