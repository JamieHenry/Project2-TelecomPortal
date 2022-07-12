import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ActiveFee } from '../models/active-fee.model';
import { HttpClient, HttpResponse } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class ActiveFeeService {

  url: string = 'http://localhost:8080/backend/api/activefee';

  constructor(private http: HttpClient) { }

  findAll(): Observable<HttpResponse<ActiveFee[]>> {
    return this.http.get<ActiveFee[]>(this.url, { observe: 'response' })
  }

  find(planId: number): Observable<HttpResponse<ActiveFee>> {
    return this.http.get<ActiveFee>(this.url + `?planId=${planId}`, { observe: 'response' })
  }
}
