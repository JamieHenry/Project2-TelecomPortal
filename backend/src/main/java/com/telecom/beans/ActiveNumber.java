package com.telecom.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ActiveNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String phoneNumber;
	@Column
	private boolean hasDeviceAssigned;
	@OneToOne
	@JoinColumn(name = "device_id")
	private Device device;
	@ManyToOne
	@JoinColumn(name = "active_plan_id")
	@JsonIgnore
	private ActivePlan activePlan;
	
	public ActiveNumber() { }

	public ActiveNumber(int id, String phoneNumber, boolean hasDeviceAssigned, Device device,
			ActivePlan activePlan) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.hasDeviceAssigned = hasDeviceAssigned;
		this.device = device;
		this.activePlan = activePlan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isHasDeviceAssigned() {
		return hasDeviceAssigned;
	}

	public void setHasDeviceAssigned(boolean hasDeviceAssigned) {
		this.hasDeviceAssigned = hasDeviceAssigned;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public ActivePlan getActivePlan() {
		return activePlan;
	}

	public void setActivePlan(ActivePlan activePlan) {
		this.activePlan = activePlan;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((device == null) ? 0 : device.hashCode());
		result = prime * result + (hasDeviceAssigned ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActiveNumber other = (ActiveNumber) obj;
		if (activePlan == null) {
			if (other.activePlan != null)
				return false;
		} else if (!activePlan.equals(other.activePlan))
			return false;
		if (device == null) {
			if (other.device != null)
				return false;
		} else if (!device.equals(other.device))
			return false;
		if (hasDeviceAssigned != other.hasDeviceAssigned)
			return false;
		if (id != other.id)
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}
}
