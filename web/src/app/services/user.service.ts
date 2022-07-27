import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { AuthResponse } from '../models/auth-response.model';
import { ActivePlan } from '../models/active-plan.model';

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

  register(registerRequest: any): Observable<HttpResponse<User>> {
    return this.http.post<User>(this.url + 'register', registerRequest, { observe: 'response' });
  }

  login(email: string, password: string): Observable<HttpResponse<AuthResponse>> {
    return this.http.post<AuthResponse>(this.url + 'login', {
      email,
      password
    }, { observe: 'response' });
  }

  addPlan(userId: number, planId: number): Observable<HttpResponse<User>> {
    return this.http.post<User>(this.url + 'addplan', {
      userId,
      planId
    }, { observe: 'response' });
  }

  assignPlan(activePlanId: number, planId: number): Observable<HttpResponse<ActivePlan>> {
    return this.http.post<ActivePlan>(this.url + 'assignplan', {
      activePlanId,
      planId
    }, { observe: 'response' });
  }

  addLine(userId: number, activePlanId: number, phoneNumber: string): Observable<HttpResponse<User>> {
    return this.http.post<User>(this.url + 'addline', {
      userId,
      activePlanId,
      phoneNumber
    }, { observe: 'response' });
  }

  removeLine(activeNumberId: number): Observable<HttpResponse<void>> {
    return this.http.delete<void>(this.url + `removeline/${activeNumberId}`, { observe: 'response' });
  }

  removePlan(activePlanId: number): Observable<HttpResponse<void>> {
    return this.http.delete<void>(this.url + `removeplan/${activePlanId}`, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<void>> {
    return this.http.delete<void>(this.url + `${id}`, { observe: 'response' });
  }
}
