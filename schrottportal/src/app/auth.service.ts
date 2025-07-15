import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';

export const AUTH_TOKEN = 'schrottverwaltung_auth';

type Credentials = {
  email: string;
  password: string;
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  http = inject(HttpClient);
  router = inject(Router);

  saveCredentials(creds: Credentials) {
    localStorage.setItem(AUTH_TOKEN, btoa(`${creds.email}:${creds.password}`));
  }

  deleteCredentials() {
    localStorage.removeItem(AUTH_TOKEN);
  }

  redirect() {
    this.router.navigate(['/uebersicht']).then();
  }

  login(creds: Credentials): Observable<void> {
    this.saveCredentials(creds);
    return this.http
      .get<void>('/api/auth/validate')
      .pipe(tap(() => this.redirect()));
  }

  signup(dto: {
    email: string;
    firstname: string;
    lastname: string;
    password: string;
  }): Observable<void> {
    return this.http.post<void>('/api/user/register', dto).pipe(
      tap(() => {
        this.saveCredentials(dto);
        this.redirect();
      }),
    );
  }
}
