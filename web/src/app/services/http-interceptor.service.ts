import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor {

  authToken: string | null = '';

  constructor(private authService: AuthenticationService) { }
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    
    this.authService.authToken.subscribe(currToken => {
      this.authToken = currToken;
    })

    if (this.authService.isUserLoggedIn()) {
      const authReq = req.clone({
        headers: new HttpHeaders({
          'Authorization': `Bearer ${this.authToken}`
        })
      });

      return next.handle(authReq);
    } else {
      return next.handle(req);
    }
  }
}
