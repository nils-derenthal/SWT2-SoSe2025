import { catchError, EMPTY, Observable, throwError } from 'rxjs';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandlerFn,
  HttpRequest,
} from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { AUTH_TOKEN } from './auth.service';

export function auth(
  req: HttpRequest<unknown>,
  next: HttpHandlerFn,
): Observable<HttpEvent<unknown>> {
  // ignore /signup
  if (req.url.endsWith('/user/register')) return next(req);

  const router = inject(Router);
  const redirect = () => router.navigate(['/login']);

  const value = localStorage.getItem(AUTH_TOKEN);
  if (!value) {
    redirect().then();
    return EMPTY;
  }

  return next(
    req.clone({
      headers: req.headers.append('Authorization', `Basic ${value}`),
    }),
  ).pipe(
    catchError(x => {
      if (x instanceof HttpErrorResponse && x.status === 403) {
        redirect().then();
      }
      return throwError(() => x);
    }),
  );
}
