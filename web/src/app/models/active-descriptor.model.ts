import { Descriptor } from "./descriptor.model";

export class ActiveDescriptor {

    id: number;
    descriptor: Descriptor;

    constructor(id: number,
                descriptor: Descriptor) {

        this.id = id;
        this.descriptor = descriptor;
    }
}
