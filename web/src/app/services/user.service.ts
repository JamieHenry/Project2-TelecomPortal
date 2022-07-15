import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { HttpClient, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url: string = 'http://localhost:8080/api/user/';

  constructor(private http: HttpClient) { }

  findAll(): Observable<HttpResponse<User[]>> {
    return this.http.get<User[]>(this.url, { observe: 'response' });
  }

  findByEmail(email: string): Observable<HttpResponse<User>> {
    return this.http.get<User>(this.url + `email/${email}`, { observe: 'response' });
  }

  findById(id: number): Observable<HttpResponse<User>> {
    return this.http.get<User>(this.url + `id/${id}`, { observe: 'response' });
  }

  register(user: User): Observable<HttpResponse<User>> {
    return this.http.post<User>(this.url + 'register', user, { observe: 'response' });
  }

  login(email: string, password: string): Observable<HttpResponse<User>> {
    return this.http.post<User>(this.url + 'login', {
      email,
      password
    }, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<User>> {
    return this.http.delete<User>(this.url + `${id}`, { observe: 'response' });
  }
}
