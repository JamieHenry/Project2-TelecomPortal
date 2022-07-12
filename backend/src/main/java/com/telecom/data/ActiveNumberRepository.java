package com.telecom.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telecom.beans.ActiveNumber;

@Repository
public interface ActiveNumberRepository extends JpaRepository<ActiveNumber, Integer> {

}
