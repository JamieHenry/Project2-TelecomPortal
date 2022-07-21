export class ActiveNumber {

    id: number;
    phoneNumber: string;
    userId: number;
    deviceId: number;
    activePlanId: number;

    constructor (id: number, phoneNumber: string, userId: number, deviceId: number, activePlanId: number) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.deviceId = deviceId;
        this.activePlanId = activePlanId;
    }
}
