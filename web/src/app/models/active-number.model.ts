import { Device } from "./device.model";

export class ActiveNumber {

    id: number;
    phoneNumber: string;
    hasDeviceAssigned: boolean;
    device: Device;

    constructor(id: number,
                phoneNumber: string,
                hasDeviceAssigned: boolean,
                device: Device) {

        this.id = id;
        this.phoneNumber = phoneNumber;
        this.hasDeviceAssigned = hasDeviceAssigned;
        this.device = device;
    }
}
