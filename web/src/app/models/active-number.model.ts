export class ActiveNumber {

    phoneNumber: string;
    userId: number;
    deviceId: number;
    activePlanId: number;

    constructor (phoneNumber: string, userId: number, deviceId: number, activePlanId: number) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.deviceId = deviceId;
        this.activePlanId = activePlanId;
    }
}
