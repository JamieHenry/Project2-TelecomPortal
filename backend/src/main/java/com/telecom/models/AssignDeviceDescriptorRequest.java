package com.telecom.models;

public class AssignDeviceDescriptorRequest {
    
    private int deviceId;

    private int descriptorId;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getDescriptorId() {
        return descriptorId;
    }

    public void setDescriptorId(int descriptorId) {
        this.descriptorId = descriptorId;
    }
}
