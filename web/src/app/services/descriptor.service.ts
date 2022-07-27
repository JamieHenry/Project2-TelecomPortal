import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Descriptor } from '../models/descriptor.model';
import { HttpClient, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DescriptorService {

  url: string = 'http://localhost:8080/api/descriptor/';

  constructor(private http: HttpClient) { }

  findAll(): Observable<HttpResponse<Descriptor[]>> {
    return this.http.get<Descriptor[]>(this.url, { observe: 'response' });
  }

  findById(id: number): Observable<HttpResponse<Descriptor>> {
    return this.http.get<Descriptor>(this.url + `id/${id}`, { observe: 'response' });
  }

  save(descriptor: Descriptor): Observable<HttpResponse<Descriptor>> {
    return this.http.post<Descriptor>(this.url, descriptor, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<void>> {
    return this.http.delete<void>(this.url + `${id}`, { observe: 'response' });
  }
}
