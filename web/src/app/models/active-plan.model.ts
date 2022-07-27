import { ActiveNumber } from "./active-number.model";
import { Plan } from "./plan.model";

export class ActivePlan {

    id: number;
    plan: Plan;
    activeNumbers: ActiveNumber[];

    constructor(id: number,
                plan: Plan,
                activeNumbers: ActiveNumber[]) {

        this.id = id;
        this.plan = plan;
        this.activeNumbers = activeNumbers;
    }
}
