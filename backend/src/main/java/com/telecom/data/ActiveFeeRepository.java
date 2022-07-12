package com.telecom.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.beans.ActiveFee;

@Repository
public interface ActiveFeeRepository extends JpaRepository<ActiveFee, Integer> {

}
