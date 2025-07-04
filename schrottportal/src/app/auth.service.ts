import {inject, Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

export const AUTH_TOKEN = 'schrottverwaltung_auth'

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  http = inject(HttpClient);

  login(email: string, password: string) {
    localStorage.setItem(AUTH_TOKEN, `${email}:${password}`);
  }

  signup(dto: { email: string, firstname: string, lastname: string, password: string}) {
    this.http.post('/api/auth/register', dto)
      .subscribe(() => { /* TODO */ })
  }
}
