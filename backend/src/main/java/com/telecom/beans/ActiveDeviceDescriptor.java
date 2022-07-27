package com.telecom.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ActiveDeviceDescriptor {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "device_id")
	@JsonIgnore
	private Device device;
	@ManyToOne
	@JoinColumn(name = "descriptor_id")
	private Descriptor descriptor;

    public ActiveDeviceDescriptor() { }

    public ActiveDeviceDescriptor(int id, Device device, Descriptor descriptor) {
        this.id = id;
        this.device = device;
        this.descriptor = descriptor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Descriptor getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(Descriptor descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((descriptor == null) ? 0 : descriptor.hashCode());
        result = prime * result + ((device == null) ? 0 : device.hashCode());
        result = prime * result + id;
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
        ActiveDeviceDescriptor other = (ActiveDeviceDescriptor) obj;
        if (descriptor == null) {
            if (other.descriptor != null)
                return false;
        } else if (!descriptor.equals(other.descriptor))
            return false;
        if (device == null) {
            if (other.device != null)
                return false;
        } else if (!device.equals(other.device))
            return false;
        if (id != other.id)
            return false;
        return true;
    }
}
