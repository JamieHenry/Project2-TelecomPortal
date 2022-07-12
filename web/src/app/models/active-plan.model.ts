export class ActivePlan {

    id: number;
    userId: number;
    planId: number;

    constructor (id: number, userId: number, planId: number) {
        this.id = id;
        this.userId = userId;
        this.planId = planId;
    }
}
