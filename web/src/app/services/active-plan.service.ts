import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ActivePlan } from '../models/active-plan.model';
import { HttpClient, HttpResponse } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class ActivePlanService {

  url: string = 'http://localhost:8080/api/activeplan';

  constructor(private http: HttpClient) { }

  findAll(): Observable<HttpResponse<ActivePlan[]>> {
    return this.http.get<ActivePlan[]>(this.url, { observe: 'response' })
  }

  find(userId: number): Observable<HttpResponse<ActivePlan>> {
    return this.http.get<ActivePlan>(this.url + `?userId=${userId}`, { observe: 'response' })
  }
}
