package com.telecom.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ActiveNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String phoneNumber;
	@Column
	private int userId;
	@Column
	private int deviceId;
	@Column
	private int activePlanId;
	
	public ActiveNumber() { }

	public ActiveNumber(int id, String phoneNumber, int userId, int deviceId, int activePlanId) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.userId = userId;
		this.deviceId = deviceId;
		this.activePlanId = activePlanId;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public int getActivePlanId() {
		return activePlanId;
	}

	public void setActivePlanId(int activePlanId) {
		this.activePlanId = activePlanId;
	}

	@Override
	public String toString() {
		return "ActiveNumber [activePlanId=" + activePlanId + ", deviceId=" + deviceId + ", id=" + id + ", phoneNumber="
				+ phoneNumber + ", userId=" + userId + "]";
	}
}
