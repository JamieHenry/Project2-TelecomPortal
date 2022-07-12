package com.telecom.beans;

public class ActivePlan {

	private int id;
	private int userId;
	private int planId;
	
	public ActivePlan() { }

	public ActivePlan(int id, int userId, int planId) {
		super();
		this.id = id;
		this.userId = userId;
		this.planId = planId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	@Override
	public String toString() {
		return "ActivePlan [id=" + id + ", userId=" + userId + ", planId=" + planId + "]";
	}
}
