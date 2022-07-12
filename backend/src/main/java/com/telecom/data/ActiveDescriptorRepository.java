package com.telecom.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.beans.ActiveDescriptor;

@Repository
public interface ActiveDescriptorRepository extends JpaRepository<ActiveDescriptor, Integer> {

}
