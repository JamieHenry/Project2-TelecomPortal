package com.telecom.beans;

public class ActiveDescriptor {

	private int id;
	private int planId;
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
