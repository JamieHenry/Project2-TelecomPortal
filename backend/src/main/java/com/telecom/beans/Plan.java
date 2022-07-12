package com.telecom.beans;

public class Plan {

	private int id;
	private String name;
	private int numDevices;
	private double price;
	
	public Plan() { }

	public Plan(int id, String name, int numDevices, double price) {
		super();
		this.id = id;
		this.name = name;
		this.numDevices = numDevices;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumDevices() {
		return numDevices;
	}

	public void setNumDevices(int numDevices) {
		this.numDevices = numDevices;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Plan [id=" + id + ", name=" + name + ", numDevices=" + numDevices + ", price=" + price + "]";
	}
}
