import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ActiveDescriptor } from '../models/active-descriptor.model';
import { HttpClient, HttpResponse } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class ActiveDescriptorService {

  url: string = 'http://localhost:8080/backend/api/activedescriptor';

  constructor(private http: HttpClient) { }

  findAll(): Observable<HttpResponse<ActiveDescriptor[]>> {
    return this.http.get<ActiveDescriptor[]>(this.url, { observe: 'response' })
  }

  find(planId: number): Observable<HttpResponse<ActiveDescriptor>> {
    return this.http.get<ActiveDescriptor>(this.url + `?planId=${planId}`, { observe: 'response' })
  }
}
