import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { HttpClient, HttpResponse } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url: string = 'http://localhost:8080/api/user';

  constructor(private http: HttpClient) { }

  register(user: User): Observable<HttpResponse<User>> {
    return this.http.post<User>(this.url, user, { observe: 'response' })
  }

  login(email: string, password: string): Observable<HttpResponse<User>> {
    return this.http.post<User>(this.url, {
      email,
      password
    }, { observe: 'response' })
  }
}
