package com.telecom.beans;

public class Descriptor {

	private int id;
	private String description;
	
	public Descriptor() { }

	public Descriptor(int id, String description) {
		super();
		this.id = id;
		this.description = description;
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

	@Override
	public String toString() {
		return "Descriptor [id=" + id + ", description=" + description + "]";
	}
}
