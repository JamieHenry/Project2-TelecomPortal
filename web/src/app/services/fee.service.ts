import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Fee } from '../models/fee.model';
import { HttpClient, HttpResponse } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class FeeService {

  url: string = 'http://localhost:8080/api/fee';

  constructor(private http: HttpClient) { }

  findAll(): Observable<HttpResponse<Fee[]>> {
    return this.http.get<Fee[]>(this.url, { observe: 'response' })
  }

  find(id: number): Observable<HttpResponse<Fee>> {
    return this.http.get<Fee>(this.url + `?id=${id}`, { observe: 'response' })
  }
}
