export class ActiveDescriptor {

    id: number;
    planId: number;
    descriptorId: number;

    constructor (id: number, planId: number, descriptorId: number) {
        this.id = id;
        this.planId = planId;
        this.descriptorId = descriptorId;
    }
}
