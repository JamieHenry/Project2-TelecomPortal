export class ActiveDeviceDescriptor {

    id: number;
    deviceId: number;
    descriptorId: number;

    constructor (id: number, deviceId: number, descriptorId: number) {
        this.id = id;
        this.deviceId = deviceId;
        this.descriptorId = descriptorId;
    }
}
