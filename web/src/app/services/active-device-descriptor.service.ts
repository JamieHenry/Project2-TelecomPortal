import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ActiveDeviceDescriptor } from '../models/active-device-descriptor.model';

@Injectable({
  providedIn: 'root'
})
export class ActiveDeviceDescriptorService {
  
  url: string = 'http://localhost:8080/api/activedevicedescriptor/';

  constructor(private http: HttpClient) { }

  findAll(): Observable<HttpResponse<ActiveDeviceDescriptor[]>> {
    return this.http.get<ActiveDeviceDescriptor[]>(this.url, { observe: 'response' });
  }

  findByDeviceId(deviceId: number): Observable<HttpResponse<ActiveDeviceDescriptor[]>> {
    return this.http.get<ActiveDeviceDescriptor[]>(this.url + `deviceid/${deviceId}`, { observe: 'response' });
  }

  findById(id: number): Observable<HttpResponse<ActiveDeviceDescriptor>> {
    return this.http.get<ActiveDeviceDescriptor>(this.url + `id/${id}`, { observe: 'response' });
  }

  save(activeDeviceDescriptor: ActiveDeviceDescriptor): Observable<HttpResponse<ActiveDeviceDescriptor>> {
    return this.http.post<ActiveDeviceDescriptor>(this.url, activeDeviceDescriptor, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<ActiveDeviceDescriptor>> {
    return this.http.delete<ActiveDeviceDescriptor>(this.url + `${id}`, { observe: 'response' });
  }
}
