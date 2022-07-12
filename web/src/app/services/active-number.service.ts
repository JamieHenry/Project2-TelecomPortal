import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ActiveNumber } from '../models/active-number.model';
import { HttpClient, HttpResponse } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class ActiveNumberService {

  url: string = 'http://localhost:8080/backend/api/activenumber';

  constructor(private http: HttpClient) { }

  findAll(): Observable<HttpResponse<ActiveNumber[]>> {
    return this.http.get<ActiveNumber[]>(this.url, { observe: 'response' })
  }

  find(userId: number): Observable<HttpResponse<ActiveNumber>> {
    return this.http.get<ActiveNumber>(this.url + `?userId=${userId}`, { observe: 'response' })
  }
}