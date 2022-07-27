import { Fee } from "./fee.model";

export class ActiveFee {

    id: number;
    fee: Fee;

    constructor(id: number,
                fee: Fee) {

        this.id = id;
        this.fee = fee;
    }
}
