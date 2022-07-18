package com.telecom.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.beans.ActiveDeviceDescriptor;

@Repository
public interface ActiveDeviceDescriptorRepository extends JpaRepository<ActiveDeviceDescriptor, Integer> {
    
    List<ActiveDeviceDescriptor> findByDeviceId(int deviceId);
}
