import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthHtppInterceptorService implements HttpInterceptor {

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {

    if (sessionStorage.getItem('email') && sessionStorage.getItem('token')) {
      req = req.clone({
        setHeaders: {
          Authorization: `${sessionStorage.getItem('token')}` 
        }
      })
    }

    return next.handle(req);

  }
}
