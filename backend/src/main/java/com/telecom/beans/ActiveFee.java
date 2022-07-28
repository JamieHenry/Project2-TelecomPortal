package com.telecom.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ActiveFee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "fee_id")
	private Fee fee;
	@ManyToOne
	@JoinColumn(name = "plan_id")
	@JsonIgnore
	private Plan plan;
	
	public ActiveFee() { }

	public ActiveFee(int id, Fee fee, Plan plan) {
		this.id = id;
		this.fee = fee;
		this.plan = plan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Fee getFee() {
		return fee;
	}

	public void setFee(Fee fee) {
		this.fee = fee;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fee == null) ? 0 : fee.hashCode());
		result = prime * result + id;
		result = prime * result + ((plan == null) ? 0 : plan.hashCode());
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
		ActiveFee other = (ActiveFee) obj;
		if (fee == null) {
			if (other.fee != null)
				return false;
		} else if (!fee.equals(other.fee))
			return false;
		if (id != other.id)
			return false;
		if (plan == null) {
			if (other.plan != null)
				return false;
		} else if (!plan.equals(other.plan))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ActiveFee [fee=" + fee + ", id=" + id + "]";
	}
}
