import { Descriptor } from "./descriptor.model";

export class ActiveDeviceDescriptor {

    id: number;
    descriptor: Descriptor;

    constructor(id: number,
                descriptor: Descriptor) {

        this.id = id;
        this.descriptor = descriptor;
    }
}
