import { ActiveDeviceDescriptor } from "./active-device-descriptor.model";

export class Device {

    id: number;
    make: string;
    model: string;
    activeDeviceDescriptors: ActiveDeviceDescriptor[];

    constructor(id: number,
                make: string,
                model: string,
                activeDeviceDescriptors: ActiveDeviceDescriptor[]) {
    
        this.id = id;
        this.make = make;
        this.model = model;
        this.activeDeviceDescriptors = activeDeviceDescriptors;
    }
}
