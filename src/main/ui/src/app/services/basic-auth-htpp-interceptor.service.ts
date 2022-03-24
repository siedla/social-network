import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthHtppInterceptorService implements HttpInterceptor {

  constructor() { }

  // intercept(req: HttpRequest<any>, next: HttpHandler) {

  //   if (sessionStorage.getItem('email') && sessionStorage.getItem('token')) {
  //     req = req.clone({
  //       setHeaders: {
  //         Authorization: `${sessionStorage.getItem('token')}` 
  //       }
  //     })
  //   }

  //   return next.handle(req);

  // }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Clone the request to add the new header
    const clonedRequest = req.clone({ headers: req.headers.append('Authorization', `${sessionStorage.getItem('token')}`) });

    // Pass the cloned request instead of the original request to the next handle
    return next.handle(clonedRequest);
  }
}
