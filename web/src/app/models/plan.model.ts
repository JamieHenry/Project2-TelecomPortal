import { ActiveDescriptor } from "./active-descriptor.model";
import { ActiveFee } from "./active-fee.model";

export class Plan {

    id: number;
    name: string;
    numDevices: number;
    price: number;
    activeDescriptors: ActiveDescriptor[];
    activeFees: ActiveFee[];

    constructor(id: number,
                name: string,
                numDevices: number,
                price: number,
                activeDescriptors: ActiveDescriptor[],
                activeFees: ActiveFee[]) {
        
        this.id = id;
        this.name = name;
        this.numDevices = numDevices;
        this.price = price;
        this.activeDescriptors = activeDescriptors;
        this.activeFees = activeFees;
    }
}
