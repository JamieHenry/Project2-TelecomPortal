package com.telecom.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class ActiveNumber {

	@Id
	private String phoneNumber;
	@Column
	private int userId;
	@Column
	private int deviceId;
	@Column
	private int planId;
	
	public ActiveNumber() { }

	public ActiveNumber(String phoneNumber, int userId, int deviceId, int planId) {
		super();
		this.phoneNumber = phoneNumber;
		this.userId = userId;
		this.deviceId = deviceId;
		this.planId = planId;
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
	
	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	@Override
	public String toString() {
		return "ActiveNumber [phoneNumber=" + phoneNumber + ", userId=" + userId + ", deviceId=" + deviceId
				+ ", planId=" + planId + "]";
	}
}
