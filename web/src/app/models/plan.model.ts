export class Plan {

    id: number;
    name: string;
    numDevices: number;
    price: number;

    constructor (id: number, name: string, numDevices: number, price: number) {
        this.id = id;
        this.name = name;
        this.numDevices = numDevices;
        this.price = price;
    }
}
