package com.telecom.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.beans.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

}
