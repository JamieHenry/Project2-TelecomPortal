package com.telecom.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.beans.ActivePlan;

@Repository
public interface ActivePlanRepository extends JpaRepository<ActivePlan, Integer> {

	List<ActivePlan> findByUserId(int userId);
}
