import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private currentUserSubject: BehaviorSubject<User | null>;
  public currentUser: Observable<User | null>;

  private authTokenSubject: BehaviorSubject<string | null>;
  public authToken: Observable<string | null>;

  constructor() {
    this.currentUserSubject = new BehaviorSubject<User | null>(
      JSON.parse(sessionStorage.getItem('currentUser')!)
    );

    this.authTokenSubject = new BehaviorSubject<string | null>(
      JSON.parse(sessionStorage.getItem('authToken')!)
    );

    this.currentUser = this.currentUserSubject.asObservable();
    this.authToken = this.authTokenSubject.asObservable();
  }

  setValues(user: User, authToken: string) {
    this.resetCredentials();
    sessionStorage.setItem('currentUser', JSON.stringify(user));
    sessionStorage.setItem('authToken', JSON.stringify(authToken));
    this.currentUserSubject.next(user);
    this.authTokenSubject.next(authToken);
  }

  isUserLoggedIn() {
    const user = sessionStorage.getItem('currentUser');
    if (user === null) return false;
    return true;
  }

  resetCredentials() {
    sessionStorage.removeItem('currentUser');
    sessionStorage.removeItem('authToken');
    this.currentUserSubject.next(null);
    this.authTokenSubject.next(null);
  }

  logout() {
    this.resetCredentials();
  }
}
