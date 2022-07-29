package com.telecom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.beans.ActiveDeviceDescriptor;
import com.telecom.data.ActiveDeviceDescriptorRepository;

@Service
public class ActiveDeviceDescriptorService {
    
    @Autowired
	private ActiveDeviceDescriptorRepository repository;

    public List<ActiveDeviceDescriptor> findAll() {
		return repository.findAll();
	}

	public ActiveDeviceDescriptor save(ActiveDeviceDescriptor activeDeviceDescriptor) {
		return repository.save(activeDeviceDescriptor);
	}
}
