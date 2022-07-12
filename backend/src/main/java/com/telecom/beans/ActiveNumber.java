package com.telecom.beans;

public class ActiveNumber {

	private String phoneNumber;
	private int userId;
	private int deviceId;
	
	public ActiveNumber() { }

	public ActiveNumber(String phoneNumber, int userId, int deviceId) {
		super();
		this.phoneNumber = phoneNumber;
		this.userId = userId;
		this.deviceId = deviceId;
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

	@Override
	public String toString() {
		return "ActiveNumber [phoneNumber=" + phoneNumber + ", userId=" + userId + ", deviceId=" + deviceId + "]";
	}
}
