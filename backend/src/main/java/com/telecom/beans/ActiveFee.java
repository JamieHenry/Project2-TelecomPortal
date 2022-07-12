package com.telecom.beans;

public class ActiveFee {

	private int id;
	private int feeId;
	private int planId;
	
	public ActiveFee() { }

	public ActiveFee(int id, int feeId, int planId) {
		super();
		this.id = id;
		this.feeId = feeId;
		this.planId = planId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFeeId() {
		return feeId;
	}

	public void setFeeId(int feeId) {
		this.feeId = feeId;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	@Override
	public String toString() {
		return "ActiveFee [id=" + id + ", feeId=" + feeId + ", planId=" + planId + "]";
	}
}
