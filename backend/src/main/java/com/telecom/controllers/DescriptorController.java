package com.telecom.controllers;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
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

import com.telecom.beans.ActiveDeviceDescriptor;
import com.telecom.beans.Descriptor;
import com.telecom.beans.Device;
import com.telecom.models.AssignDeviceDescriptorRequest;
import com.telecom.services.ActiveDeviceDescriptorService;
import com.telecom.services.DescriptorService;
import com.telecom.services.DeviceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/descriptor")
@Tag(name = "Descriptor")
@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Successful"),
    @ApiResponse(responseCode = "201", description = "Created"),
	@ApiResponse(responseCode = "401", description = "Unauthorized")
})
@SecurityRequirement(name = "JWT Authentication")
public class DescriptorController {
    
    private static final Logger logger = Logger.getLogger(DescriptorController.class);

    @Autowired
    private DescriptorService descriptorService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ActiveDeviceDescriptorService activeDeviceDescriptorService;

    @GetMapping("/id/{id}")
    @Operation(summary = "Find Descriptor by id", description = "Return Descriptor with matching id")
    public ResponseEntity<Optional<Descriptor>> findById(@PathVariable(value="id") int id) {
        logger.info("Find Descriptor by id: " + id);
        return new ResponseEntity<Optional<Descriptor>>(descriptorService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    @Operation(summary = "Find all Descriptors", description = "Return all Descriptors")
    public ResponseEntity<List<Descriptor>> findAll() {
        logger.info("Find all Descriptors");
        return new ResponseEntity<List<Descriptor>>(descriptorService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/assigndevice")
    @Operation(summary = "Assign a specified Descriptor to a Device", description = "Assign a specified Descriptor to a specified Device")
    public ResponseEntity<ActiveDeviceDescriptor> assignDescriptor(@RequestBody AssignDeviceDescriptorRequest assignDeviceDescriptorRequest) {
        logger.info("Assign a new DeviceDescriptor to a Device");
        Device device = deviceService.findById(assignDeviceDescriptorRequest.getDeviceId()).get();
        logger.info("Device found from request: " + device);
        Descriptor descriptor = descriptorService.findById(assignDeviceDescriptorRequest.getDescriptorId()).get();
        logger.info("Descriptor found from request: " + descriptor);
        return new ResponseEntity<ActiveDeviceDescriptor>(activeDeviceDescriptorService.save(new ActiveDeviceDescriptor(0, device, descriptor)), HttpStatus.CREATED);
    }

    @PostMapping("/")
    @Operation(summary = "Save a new Descriptor", description = "Save and return a new Descriptor")
    public ResponseEntity<Descriptor> save(@RequestBody Descriptor descriptor) {
        logger.info("Save new Descriptor: " + descriptor);
        return new ResponseEntity<Descriptor>(descriptorService.save(descriptor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Descriptor by id", description = "Delete Descriptor with matching id")
    public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
        logger.info("Remove Descriptor with id: " + id);
        descriptorService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
