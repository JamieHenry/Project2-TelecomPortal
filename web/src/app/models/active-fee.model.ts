export class ActiveFee {

    id: number;
    feeId: number;
    planId: number;

    constructor (id: number, feeId: number, planId: number) {
        this.id = id;
        this.feeId = feeId;
        this.planId = planId;
    }
}
