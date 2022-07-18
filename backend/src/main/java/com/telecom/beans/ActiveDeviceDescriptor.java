package com.telecom.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ActiveDeviceDescriptor {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private int deviceId;
	@Column
	private int descriptorId;

    public ActiveDeviceDescriptor() { }

    public ActiveDeviceDescriptor(int id, int deviceId, int descriptorId) {
        super();
        this.id = id;
        this.deviceId = deviceId;
        this.descriptorId = descriptorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "ActiveDeviceDescriptor [descriptorId=" + descriptorId + ", deviceId=" + deviceId + ", id=" + id + "]";
    }
}
