import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private currentUserSubject: BehaviorSubject<User | null>;
  public currentUser: Observable<User | null>;

  public authToken = '';

  constructor() {
    this.currentUserSubject = new BehaviorSubject<User | null>(
      JSON.parse(sessionStorage.getItem('currentUser')!)
    );

    this.currentUser = this.currentUserSubject.asObservable();
  }

  setUser(user: User) {
    this.resetCredentials();
    sessionStorage.setItem('currentUser', JSON.stringify(user));
    this.currentUserSubject.next(user);
  }

  isUserLoggedIn() {
    const user = sessionStorage.getItem('currentUser');
    if (user === null) return false;
    return true;
  }

  resetCredentials() {
    sessionStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

  logout() {
    this.resetCredentials();
  }
}
