export class AuthResponse {

    user: {
        id: number,
        email: string,
        firstName: string,
        lastName: string,
        password: string,
        enabled: boolean,
        username: string,
        authorities: any,
        accountNonLocked: boolean,
        accountNonExpired: boolean,
        credentialsNonExpired: boolean
    };
    accessToken: string;

    constructor(user: {
        id: number,
        email: string,
        firstName: string,
        lastName: string,
        password: string,
        enabled: boolean,
        username: string,
        authorities: any,
        accountNonLocked: boolean,
        accountNonExpired: boolean,
        credentialsNonExpired: boolean
    }, accessToken: string) {
        this.user = user;
        this.accessToken = accessToken;
    }
}
