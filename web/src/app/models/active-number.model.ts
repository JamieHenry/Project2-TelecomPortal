import { Device } from "./device.model";

export class ActiveNumber {

    id: number;
    phoneNumber: string;
    hasDeviceAssigned: boolean;
    userId: number;
    device: Device;

    constructor(id: number,
                phoneNumber: string,
                hasDeviceAssigned: boolean,
                userId: number,
                device: Device) {

        this.id = id;
        this.phoneNumber = phoneNumber;
        this.hasDeviceAssigned = hasDeviceAssigned;
        this.userId = userId;
        this.device = device;
    }
}
