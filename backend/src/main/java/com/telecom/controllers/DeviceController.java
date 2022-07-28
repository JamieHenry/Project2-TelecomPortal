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

import com.telecom.beans.ActiveNumber;
import com.telecom.beans.Device;
import com.telecom.models.AssignLineRequest;
import com.telecom.models.MakeAndModelRequest;
import com.telecom.services.ActiveNumberService;
import com.telecom.services.DeviceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/device")
@Tag(name = "Device")
@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Successful"),
    @ApiResponse(responseCode = "201", description = "Created"),
	@ApiResponse(responseCode = "401", description = "Unauthorized")
})
@SecurityRequirement(name = "JWT Authentication")
public class DeviceController {
    
    private static final Logger logger = Logger.getLogger(DeviceController.class);

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ActiveNumberService activeNumberService;

    @PostMapping("/makemodel")
    @Operation(summary = "Find Device by make and model", description = "Return Device with matching make and model")
    public ResponseEntity<Optional<Device>> findByModel(@RequestBody MakeAndModelRequest makeAndModelRequest) {
        logger.info("Find Device by make and model: " + makeAndModelRequest);
        return new ResponseEntity<Optional<Device>>(deviceService.findByMakeAndModel(makeAndModelRequest.getMake(), makeAndModelRequest.getModel()), HttpStatus.OK);
    }

    @GetMapping("/phonenumber/{phoneNumber}")
    @Operation(summary = "Find Active Number by phoneNumber", description = "Return Device with matching phoneNumber")
    public ResponseEntity<Optional<ActiveNumber>> findByPhoneNumber(@PathVariable(value="phoneNumber") String phoneNumber) {
        logger.info("Find Active Number by phoneNumber: " + phoneNumber);
        return new ResponseEntity<Optional<ActiveNumber>>(activeNumberService.findByPhoneNumber(phoneNumber), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Find Device by id", description = "Return Device with matching id")
    public ResponseEntity<Optional<Device>> findById(@PathVariable(value="id") int id) {
        logger.info("Find Device by id: " + id);
        return new ResponseEntity<Optional<Device>>(deviceService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    @Operation(summary = "Find all Devices", description = "Return all Devices")
    public ResponseEntity<List<Device>> findAll() {
        logger.info("Find all Devices");
        return new ResponseEntity<List<Device>>(deviceService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/assignline")
    @Operation(summary = "Assign an Active Number to a specific Device", description = "Save specified Device to specified Active Number")
    public ResponseEntity<Device> assignLine(@RequestBody AssignLineRequest assignLineRequest) {
        logger.info("Assign an Active Number to a specific Device");
        Device device = deviceService.findById(assignLineRequest.getDeviceId()).get();
        logger.info("Device found from from request: " + device);
        ActiveNumber activeNumber = activeNumberService.findByPhoneNumber(assignLineRequest.getPhoneNumber()).get();
        logger.info("Active Number found from from request: " + activeNumber);
        activeNumber.setDevice(device);
        activeNumber.setHasDeviceAssigned(true);
        activeNumberService.save(activeNumber);
        return new ResponseEntity<Device>(device, HttpStatus.CREATED);
    }

    @PostMapping("/")
    @Operation(summary = "Save a new Device", description = "Save and return a new Device")
    public ResponseEntity<Device> save(@RequestBody Device device) {
        logger.info("Save new Device: " + device);
        return new ResponseEntity<Device>(deviceService.save(device), HttpStatus.CREATED);
    }

    @DeleteMapping("/removeline/{phoneNumber}")
    @Operation(summary = "Unassign Active Number to a Device", description = "Unassign Active Number to a Device with specified phoneNumber")
    public ResponseEntity<Void> removeLine(@PathVariable(value="phoneNumber") String phoneNumber) {
        logger.info("Unassign specified Active Number");
        ActiveNumber activeNumber = activeNumberService.findByPhoneNumber(phoneNumber).get();
        logger.info("Active Number found from from request: " + activeNumber);
        activeNumber.setHasDeviceAssigned(false);
        activeNumberService.save(activeNumber);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Device by id", description = "Delete Device with matching id")
    public ResponseEntity<Void> deleteById(@PathVariable(value="id") int id) {
        logger.info("Remove Device with id: " + id);
        deviceService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
