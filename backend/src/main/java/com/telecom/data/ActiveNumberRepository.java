package com.telecom.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.beans.ActiveNumber;

@Repository
public interface ActiveNumberRepository extends JpaRepository<ActiveNumber, Integer> {

	List<ActiveNumber> findByUserId(int userId);

	List<ActiveNumber> findByActivePlanId(int activePlanId);

    Optional<ActiveNumber> findByPhoneNumber(String phoneNumber);
}
