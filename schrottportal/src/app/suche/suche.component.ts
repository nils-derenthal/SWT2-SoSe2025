import {Component, inject, model} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {ImmobilienService} from '../services/immobilien.service';
import {toObservable} from '@angular/core/rxjs-interop';
import {debounceTime, distinctUntilChanged, startWith, switchMap} from 'rxjs';
import {AsyncPipe} from '@angular/common';
import {ImmoUebersichtskarteComponent} from '../uebersicht/immo-uebersichtskarte/immo-uebersichtskarte.component';

@Component({
  selector: 'app-suche',
  imports: [FormsModule, AsyncPipe],
  imports: [
    FormsModule,
    AsyncPipe,
    ImmoUebersichtskarteComponent
  ],
  templateUrl: './suche.component.html',
  standalone: true,
  styleUrl: './suche.component.scss',
})
export class SucheComponent {
  immobilienService = inject(ImmobilienService);

  search = model<string>('');
  immobilien$ = toObservable(this.search).pipe(
    startWith(''),
    distinctUntilChanged(),
    debounceTime(300),
    switchMap((search) => this.immobilienService.getImmobilienBySearch(search)),
  );
}
