package com.telecom.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ActiveDescriptor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private int planId;
	@Column
	private int descriptorId;
	
	public ActiveDescriptor() { }

	public ActiveDescriptor(int id, int planId, int descriptorId) {
		super();
		this.id = id;
		this.planId = planId;
		this.descriptorId = descriptorId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getDescriptorId() {
		return descriptorId;
	}

	public void setDescriptorId(int descriptorId) {
		this.descriptorId = descriptorId;
	}

	@Override
	public String toString() {
		return "ActiveDescriptor [id=" + id + ", planId=" + planId + ", descriptorId=" + descriptorId + "]";
	}
}
