import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Plan } from '../models/plan.model';
import { HttpClient, HttpResponse } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class PlanService {

  url: string = 'http://localhost:8080/backend/api/plan';

  constructor(private http: HttpClient) { }

  findAll(): Observable<HttpResponse<Plan[]>> {
    return this.http.get<Plan[]>(this.url, { observe: 'response' })
  }

  find(id: number): Observable<HttpResponse<Plan>> {
    return this.http.get<Plan>(this.url + `?id=${id}`, { observe: 'response' })
  }
}
