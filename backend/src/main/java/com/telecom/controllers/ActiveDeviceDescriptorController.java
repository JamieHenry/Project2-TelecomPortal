package com.telecom.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.telecom.beans.ActiveDeviceDescriptor;
import com.telecom.services.ActiveDeviceDescriptorService;

@RestControllerAdvice
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/activedevicedescriptor")
public class ActiveDeviceDescriptorController {
    
    @Autowired
    private ActiveDeviceDescriptorService service;

    @GetMapping("/deviceid/{deviceId}")
    public ResponseEntity<List<ActiveDeviceDescriptor>> findByPlanId(@PathVariable(value="deviceId") int deviceId) {
        return new ResponseEntity<List<ActiveDeviceDescriptor>>(service.findByDeviceId(deviceId), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<ActiveDeviceDescriptor>> findById(@PathVariable(value="id") int id) {
        return new ResponseEntity<Optional<ActiveDeviceDescriptor>>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ActiveDeviceDescriptor>> findAll() {
        return new ResponseEntity<List<ActiveDeviceDescriptor>>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ActiveDeviceDescriptor> save(@RequestBody ActiveDeviceDescriptor activeDeviceDescriptor) {
        return new ResponseEntity<ActiveDeviceDescriptor>(service.save(activeDeviceDescriptor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
		service.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
