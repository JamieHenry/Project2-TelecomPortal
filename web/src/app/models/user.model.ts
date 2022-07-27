import { ActivePlan } from "./active-plan.model";

export class User {

    id: number;
    email: string;
    firstName: string;
    lastName: string;
    password: string;
    activePlans: ActivePlan[];
    enabled: boolean;
    username: string;
    authorities: any;
    accountNonLocked: boolean;
    accountNonExpired: boolean;
    credentialsNonExpired: boolean;


    constructor(id: number,
                email: string,
                firstName: string,
                lastName: string,
                password: string,
                activePlans: ActivePlan[],
                enabled: boolean,
                username: string,
                authorities: any,
                accountNonLocked: boolean,
                accountNonExpired: boolean,
                credentialsNonExpired: boolean) {

        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.activePlans = activePlans;
        this.enabled = enabled;
        this.username = username;
        this.authorities = authorities;
        this.accountNonLocked = accountNonLocked;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
    }
}
