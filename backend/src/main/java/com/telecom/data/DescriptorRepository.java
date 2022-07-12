package com.telecom.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.beans.Descriptor;

@Repository
public interface DescriptorRepository extends JpaRepository<Descriptor, Integer> {

}
