import {Component, model} from '@angular/core';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-suche',
  imports: [
    FormsModule
  ],
  templateUrl: './suche.component.html',
  styleUrl: './suche.component.scss'
})
export class SucheComponent {
  search = model<string>('');
}
