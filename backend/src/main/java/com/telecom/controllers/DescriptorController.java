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

import com.telecom.beans.Descriptor;
import com.telecom.services.DescriptorService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/descriptor")
public class DescriptorController {
    
    @Autowired
    private DescriptorService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Descriptor>> findById(@PathVariable(value="id") int id) {
        return new ResponseEntity<Optional<Descriptor>>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Descriptor>> findAll() {
        return new ResponseEntity<List<Descriptor>>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Descriptor> save(@RequestBody Descriptor descriptor) {
        return new ResponseEntity<Descriptor>(service.save(descriptor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
        service.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
