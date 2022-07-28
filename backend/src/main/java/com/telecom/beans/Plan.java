package com.telecom.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@OneToMany(mappedBy = "plan")
	@JsonIgnore
	private List<ActivePlan> activePlans;
	@OneToMany(mappedBy = "plan")
	private List<ActiveDescriptor> activeDescriptors;
	@OneToMany(mappedBy = "plan")
	private List<ActiveFee> activeFees;
	
	public Plan() { }

	public Plan(int id, String name, int numDevices, double price, List<ActivePlan> activePlans,
			List<ActiveDescriptor> activeDescriptors, List<ActiveFee> activeFees) {
		this.id = id;
		this.name = name;
		this.numDevices = numDevices;
		this.price = price;
		this.activePlans = activePlans;
		this.activeDescriptors = activeDescriptors;
		this.activeFees = activeFees;
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

	public List<ActivePlan> getActivePlans() {
		return activePlans;
	}

	public void setActivePlans(List<ActivePlan> activePlans) {
		this.activePlans = activePlans;
	}

	public List<ActiveDescriptor> getActiveDescriptors() {
		return activeDescriptors;
	}

	public void setActiveDescriptors(List<ActiveDescriptor> activeDescriptors) {
		this.activeDescriptors = activeDescriptors;
	}

	public List<ActiveFee> getActiveFees() {
		return activeFees;
	}

	public void setActiveFees(List<ActiveFee> activeFees) {
		this.activeFees = activeFees;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + numDevices;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Plan other = (Plan) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numDevices != other.numDevices)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}
}
