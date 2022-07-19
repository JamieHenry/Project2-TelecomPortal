import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ActiveDescriptor } from '../models/active-descriptor.model';
import { HttpClient, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ActiveDescriptorService {

  url: string = 'http://localhost:8080/api/activedescriptor/';

  constructor(private http: HttpClient) { }

  findAll(): Observable<HttpResponse<ActiveDescriptor[]>> {
    return this.http.get<ActiveDescriptor[]>(this.url, { observe: 'response' });
  }

  findByPlanId(planId: number): Observable<HttpResponse<ActiveDescriptor[]>> {
    return this.http.get<ActiveDescriptor[]>(this.url + `planid/${planId}`, { observe: 'response' });
  }

  findById(id: number): Observable<HttpResponse<ActiveDescriptor>> {
    return this.http.get<ActiveDescriptor>(this.url + `id/${id}`, { observe: 'response' });
  }

  save(activeDescriptor: ActiveDescriptor): Observable<HttpResponse<ActiveDescriptor>> {
    return this.http.post<ActiveDescriptor>(this.url, activeDescriptor, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<ActiveDescriptor>> {
    return this.http.delete<ActiveDescriptor>(this.url + `${id}`, { observe: 'response' });
  }
}
