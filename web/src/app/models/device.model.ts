export class Device {

    id: number;
    make: string;
    model: string;

    constructor (id: number, make: string, model: string) {
        this.id = id;
        this.make = make;
        this.model = model;
    }
}
