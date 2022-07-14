package com.telecom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecom.beans.Device;
import com.telecom.data.DeviceRepository;

@Service
public class DeviceService {

	@Autowired
	private DeviceRepository repository;
	
	public Device save(Device device) {
		return repository.save(device);
	}
	
	public Optional<Device> findByModel(String model) {
		return repository.findByModel(model);
	}
	
	public Optional<Device> findById(int id) {
		return repository.findById(id);
	}
	
	public List<Device> findAll() {
		return repository.findAll();
	}
	
	public void deleteById(int id) {
		repository.deleteById(id);
	}
}
