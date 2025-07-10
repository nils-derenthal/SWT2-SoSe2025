import {Component, inject, model, OnInit, output} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {ImmobilienService} from '../services/immobilien.service';
import {toObservable} from '@angular/core/rxjs-interop';
import {debounceTime, distinctUntilChanged, startWith, switchMap, tap} from 'rxjs';
import {AsyncPipe} from '@angular/common';
import {ImmobilieDTO} from '../models/immobilie.model';
import {ImmoUebersichtskarteComponent} from '../uebersicht/immo-uebersichtskarte/immo-uebersichtskarte.component';

@Component({
  selector: 'app-suche',
  imports: [
    FormsModule,
    AsyncPipe,
    ImmoUebersichtskarteComponent
  ],
  templateUrl: './suche.component.html',
  standalone: true,
  styleUrl: './suche.component.scss',
})
export class SucheComponent implements OnInit {
  immobilienService = inject(ImmobilienService);

  search = model<string>('');

  searchedImmobilien = output<ImmobilieDTO[]>()

  immobilien$ = toObservable(this.search)
    .pipe(
      startWith(''),
      distinctUntilChanged(),
      debounceTime(300),
      switchMap(search => this.immobilienService.getImmobilienBySearch(search)),
      tap(() => console.log('searched')),
    );

  ngOnInit(): void {
    this.immobilien$.subscribe(i => this.searchedImmobilien.emit(i));
  }
}
