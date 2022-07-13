package com.telecom.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Plan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@Column
	private int numDevices;
	@Column
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
