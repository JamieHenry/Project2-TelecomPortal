export class Fee {

    id: number;
    description: string;
    percentage: boolean;
    amount: number;

    constructor(id: number,
                description: string,
                isPercentage: boolean,
                amount: number) {
                    
        this.id = id;
        this.description = description;
        this.percentage = isPercentage;
        this.amount = amount;
    }
}
