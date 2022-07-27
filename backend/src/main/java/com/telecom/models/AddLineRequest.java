package com.telecom.models;

public class AddLineRequest {
    
    private int userId;
    
    private int activePlanId;

    private String phoneNumber;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getActivePlanId() {
        return activePlanId;
    }

    public void setActivePlanId(int activePlanId) {
        this.activePlanId = activePlanId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
