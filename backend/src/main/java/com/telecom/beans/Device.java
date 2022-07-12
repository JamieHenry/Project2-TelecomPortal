package com.telecom.beans;

public class Device {

	private int id;
	private String make;
	private String model;
	
	public Device() { }

	public Device(int id, String make, String model) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", make=" + make + ", model=" + model + "]";
	}
}
