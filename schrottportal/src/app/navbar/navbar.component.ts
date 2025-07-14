import {Component, inject} from '@angular/core';
import {RouterLink, RouterLinkActive} from '@angular/router';
import {NgOptimizedImage} from '@angular/common';
import {AuthService, AUTH_TOKEN} from '../auth.service';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, RouterLinkActive, NgOptimizedImage],
  templateUrl: './navbar.component.html',
  standalone: true,
  styleUrl: './navbar.component.scss',
})
export class NavbarComponent {
  authService = inject(AuthService);
  
  get currentUser(): string {
    const token = localStorage.getItem(AUTH_TOKEN);
    if (token) {
      const decoded = atob(token);
      return decoded.split(':')[0]; // Email extrahieren
    }
    return '';
  }
  
  logout(): void {
    localStorage.removeItem(AUTH_TOKEN);
    this.authService.router.navigate(['/login']);
  }
}
