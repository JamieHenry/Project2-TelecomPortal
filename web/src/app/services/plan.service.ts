import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Plan } from '../models/plan.model';
import { HttpClient, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PlanService {

  url: string = 'http://localhost:8080/api/plan/';

  constructor(private http: HttpClient) { }

  findAll(): Observable<HttpResponse<Plan[]>> {
    return this.http.get<Plan[]>(this.url, { observe: 'response' });
  }

  findById(id: number): Observable<HttpResponse<Plan>> {
    return this.http.get<Plan>(this.url + `id/${id}`, { observe: 'response' });
  }

  save(plan: Plan): Observable<HttpResponse<Plan>> {
    return this.http.post<Plan>(this.url, plan, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<void>> {
    return this.http.delete<void>(this.url + `${id}`, { observe: 'response' });
  }
}
