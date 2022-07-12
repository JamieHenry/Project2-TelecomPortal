export class ActiveNumber {

    phoneNumber: string;
    userId: number;
    deviceId: number;

    constructor (phoneNumber: string, userId: number, deviceId: number) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.deviceId = deviceId;
    }
}
