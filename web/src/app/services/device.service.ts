import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Device } from '../models/device.model';
import { HttpClient, HttpResponse } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

  url: string = 'http://localhost:8080/api/device';

  constructor(private http: HttpClient) { }

  findAll(): Observable<HttpResponse<Device[]>> {
    return this.http.get<Device[]>(this.url, { observe: 'response' })
  }

  find(id: number): Observable<HttpResponse<Device>> {
    return this.http.get<Device>(this.url + `?id=${id}`, { observe: 'response' })
  }
}
