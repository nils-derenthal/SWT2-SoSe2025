import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {NavbarComponent} from './navbar/navbar.component';
import {DetailansichtComponent} from './detailansicht/detailansicht.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent, DetailansichtComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'schrottportal';
}
