package com.telecom.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.beans.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {

	Device findByModel(String model);
}
