import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ActiveNumber } from '../models/active-number.model';
import { HttpClient, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ActiveNumberService {

  url: string = 'http://localhost:8080/api/activenumber/';

  constructor(private http: HttpClient) { }

  findAll(): Observable<HttpResponse<ActiveNumber[]>> {
    return this.http.get<ActiveNumber[]>(this.url, { observe: 'response' });
  }

  findByUserId(userId: number): Observable<HttpResponse<ActiveNumber[]>> {
    return this.http.get<ActiveNumber[]>(this.url + `userid/${userId}`, { observe: 'response' });
  }

  findByPlanId(planId: number): Observable<HttpResponse<ActiveNumber>> {
    return this.http.get<ActiveNumber>(this.url + `planid/${planId}`, { observe: 'response' });
  }

  findByPhoneNumber(phoneNumber: string): Observable<HttpResponse<ActiveNumber>> {
    return this.http.get<ActiveNumber>(this.url + `phonenumber/${phoneNumber}`, { observe: 'response' });
  }

  save(activeNumber: ActiveNumber): Observable<HttpResponse<ActiveNumber>> {
    return this.http.post<ActiveNumber>(this.url, activeNumber, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<ActiveNumber>> {
    return this.http.delete<ActiveNumber>(this.url + `${id}`, { observe: 'response' });
  }
}
