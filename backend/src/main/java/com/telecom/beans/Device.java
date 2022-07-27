package com.telecom.beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String make;
	@Column
	private String model;
	@OneToMany(mappedBy = "device")
	private Set<ActiveDeviceDescriptor> activeDeviceDescriptors;
	
	public Device() { }

	public Device(int id, String make, String model, Set<ActiveDeviceDescriptor> activeDeviceDescriptors) {
		this.id = id;
		this.make = make;
		this.model = model;
		this.activeDeviceDescriptors = activeDeviceDescriptors;
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

	public Set<ActiveDeviceDescriptor> getActiveDeviceDescriptors() {
		return activeDeviceDescriptors;
	}

	public void setActiveDeviceDescriptors(Set<ActiveDeviceDescriptor> activeDeviceDescriptors) {
		this.activeDeviceDescriptors = activeDeviceDescriptors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
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
		Device other = (Device) obj;
		if (id != other.id)
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		return true;
	}
}
