package com.telecom.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ActivePlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	@ManyToOne
	@JoinColumn(name = "plan_id")
	private Plan plan;
	@OneToMany(mappedBy = "activePlan")
	private List<ActiveNumber> activeNumbers;
	
	public ActivePlan() { }

	public ActivePlan(int id, User user, Plan plan, List<ActiveNumber> activeNumbers) {
		this.id = id;
		this.user = user;
		this.plan = plan;
		this.activeNumbers = activeNumbers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public List<ActiveNumber> getActiveNumbers() {
		return activeNumbers;
	}

	public void setActiveNumbers(List<ActiveNumber> activeNumbers) {
		this.activeNumbers = activeNumbers;
	}

	public void addActiveNumber(ActiveNumber activeNumber) {
		this.activeNumbers.add(activeNumber);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activeNumbers == null) ? 0 : activeNumbers.hashCode());
		result = prime * result + id;
		result = prime * result + ((plan == null) ? 0 : plan.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		ActivePlan other = (ActivePlan) obj;
		if (activeNumbers == null) {
			if (other.activeNumbers != null)
				return false;
		} else if (!activeNumbers.equals(other.activeNumbers))
			return false;
		if (id != other.id)
			return false;
		if (plan == null) {
			if (other.plan != null)
				return false;
		} else if (!plan.equals(other.plan))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ActivePlan [activeNumbers=" + activeNumbers + ", id=" + id + "]";
	}
}
