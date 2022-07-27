import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Fee } from '../models/fee.model';
import { HttpClient, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FeeService {

  url: string = 'http://localhost:8080/api/fee/';

  constructor(private http: HttpClient) { }

  findAll(): Observable<HttpResponse<Fee[]>> {
    return this.http.get<Fee[]>(this.url, { observe: 'response' });
  }

  findById(id: number): Observable<HttpResponse<Fee>> {
    return this.http.get<Fee>(this.url + `id/${id}`, { observe: 'response' });
  }

  save(fee: Fee): Observable<HttpResponse<Fee>> {
    return this.http.post<Fee>(this.url, fee, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<void>> {
    return this.http.delete<void>(this.url + `${id}`, { observe: 'response' });
  }
}
