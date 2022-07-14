import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ActiveFee } from '../models/active-fee.model';
import { HttpClient, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ActiveFeeService {

  url: string = 'http://localhost:8080/api/activefee/';

  constructor(private http: HttpClient) { }

  findAll(): Observable<HttpResponse<ActiveFee[]>> {
    return this.http.get<ActiveFee[]>(this.url, { observe: 'response' });
  }

  findByPlanId(planId: number): Observable<HttpResponse<ActiveFee>> {
    return this.http.get<ActiveFee>(this.url + `planId/${planId}`, { observe: 'response' });
  }

  findById(id: number): Observable<HttpResponse<ActiveFee>> {
    return this.http.get<ActiveFee>(this.url + `id/${id}`, { observe: 'response' });
  }

  save(activeFee: ActiveFee): Observable<HttpResponse<ActiveFee>> {
    return this.http.post<ActiveFee>(this.url, activeFee, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<ActiveFee>> {
    return this.http.delete<ActiveFee>(this.url + `${id}`, { observe: 'response' });
  }
}
