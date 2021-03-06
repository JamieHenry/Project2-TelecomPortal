package com.telecom.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.beans.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {

    Optional<Device> findByMakeAndModelIgnoreCase(String make, String model);
}
