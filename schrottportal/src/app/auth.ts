import {EMPTY, Observable} from "rxjs";
import {HttpHandlerFn, HttpRequest, HttpEvent } from "@angular/common/http";
import {inject} from "@angular/core";
import {Router} from "@angular/router";
import {AUTH_TOKEN} from "./auth.service";

export function auth(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
  // ignore /signup
  if (req.url.endsWith('/auth/register')) return next(req);

  const value = localStorage.getItem(AUTH_TOKEN);

  if (!value) {
    inject(Router).navigate(['/login'])
      .then();
    return EMPTY;
  }

  req.clone({
    headers: req.headers.append('Authorization', `Basic ${value}`)
  })

  return next(req);
}

