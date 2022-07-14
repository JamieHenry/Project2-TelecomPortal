import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ActivePlan } from '../models/active-plan.model';
import { HttpClient, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ActivePlanService {

  url: string = 'http://localhost:8080/api/activeplan/';

  constructor(private http: HttpClient) { }

  findAll(): Observable<HttpResponse<ActivePlan[]>> {
    return this.http.get<ActivePlan[]>(this.url, { observe: 'response' });
  }

  findByUserId(userId: number): Observable<HttpResponse<ActivePlan>> {
    return this.http.get<ActivePlan>(this.url + `userId/${userId}`, { observe: 'response' });
  }

  findById(id: number): Observable<HttpResponse<ActivePlan>> {
    return this.http.get<ActivePlan>(this.url + `id/${id}`, { observe: 'response' });
  }

  save(activePlan: ActivePlan): Observable<HttpResponse<ActivePlan>> {
    return this.http.post<ActivePlan>(this.url, activePlan, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<ActivePlan>> {
    return this.http.delete<ActivePlan>(this.url + `${id}`, { observe: 'response' });
  }
}
