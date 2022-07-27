import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Device } from '../models/device.model';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { ActiveNumber } from '../models/active-number.model';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

  url: string = 'http://localhost:8080/api/device/';

  constructor(private http: HttpClient) { }

  findAll(): Observable<HttpResponse<Device[]>> {
    return this.http.get<Device[]>(this.url, { observe: 'response' });
  }

  findByMakeAndModel(make:string, model: string): Observable<HttpResponse<Device>> {
    return this.http.post<Device>(this.url + 'makemodel', {
      make,
      model
    }, { observe: 'response' });
  }

  assignLine(deviceId: number, phoneNumber: string): Observable<HttpResponse<Device>> {
    return this.http.post<Device>(this.url + 'assignline', {
      deviceId,
      phoneNumber
    }, { observe: 'response' });
  }

  findByPhoneNumber(phoneNumber: string): Observable<HttpResponse<ActiveNumber>> {
    return this.http.get<ActiveNumber>(this.url + `phonenumber/${phoneNumber}`, { observe: 'response' });
  }

  findById(id: number): Observable<HttpResponse<Device>> {
    return this.http.get<Device>(this.url + `id/${id}`, { observe: 'response' });
  }

  save(device: Device): Observable<HttpResponse<Device>> {
    return this.http.post<Device>(this.url, device, { observe: 'response' });
  }

  removeLine(phoneNumber: string): Observable<HttpResponse<void>> {
    return this.http.delete<void>(this.url + `removeline/${phoneNumber}`, { observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<void>> {
    return this.http.delete<void>(this.url + `${id}`, { observe: 'response' });
  }
}
