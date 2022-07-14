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
import org.springframework.web.bind.annotation.RestController;

import com.telecom.beans.Device;
import com.telecom.services.DeviceService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/device")
public class DeviceController {
    
    @Autowired
    private DeviceService service;

    @GetMapping("/model/{model}")
    public ResponseEntity<Optional<Device>> findByModel(@PathVariable(value="model") String model) {
        return new ResponseEntity<Optional<Device>>(service.findByModel(model), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Device>> findById(@PathVariable(value="id") int id) {
        return new ResponseEntity<Optional<Device>>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Device>> findAll() {
        return new ResponseEntity<List<Device>>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Device> save(@RequestBody Device device) {
        return new ResponseEntity<Device>(service.save(device), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
        service.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
