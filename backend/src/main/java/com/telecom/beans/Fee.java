package com.telecom.beans;

public class Fee {

	private int id;
	private String description;
	private boolean isPercentage;
	private double amount;
	
	public Fee() { }

	public Fee(int id, String description, boolean isPercentage, double amount) {
		super();
		this.id = id;
		this.description = description;
		this.isPercentage = isPercentage;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPercentage() {
		return isPercentage;
	}

	public void setPercentage(boolean isPercentage) {
		this.isPercentage = isPercentage;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Fee [id=" + id + ", description=" + description + ", isPercentage=" + isPercentage + ", amount="
				+ amount + "]";
	}
}