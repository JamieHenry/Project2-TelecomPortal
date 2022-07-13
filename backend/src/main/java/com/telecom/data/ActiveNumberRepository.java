package com.telecom.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.beans.ActiveNumber;

@Repository
public interface ActiveNumberRepository extends JpaRepository<ActiveNumber, Integer> {

	List<ActiveNumber> findByUserId(int userId);

	List<ActiveNumber> findByPlanId(int planId);
}
