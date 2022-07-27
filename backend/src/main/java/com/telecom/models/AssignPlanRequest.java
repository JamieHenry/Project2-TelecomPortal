package com.telecom.models;

public class AssignPlanRequest {
    
    private int activePlanId;

    private int planId;

    public int getActivePlanId() {
        return activePlanId;
    }

    public void setActivePlanId(int activePlanId) {
        this.activePlanId = activePlanId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }
}
