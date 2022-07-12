package com.telecom.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.beans.Fee;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Integer> {

}
