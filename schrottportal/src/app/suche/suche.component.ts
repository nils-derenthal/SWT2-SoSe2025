import {Component, inject, model, OnInit, output, signal} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {ImmobilienService} from '../services/immobilien.service';
import {toObservable} from '@angular/core/rxjs-interop';
import {debounceTime, distinctUntilChanged, startWith, switchMap, tap} from 'rxjs';
import {AsyncPipe} from '@angular/common';
import {ImmoUebersichtskarteComponent} from '../uebersicht/immo-uebersichtskarte/immo-uebersichtskarte.component';
import {ImmoStatusService} from '../services/immoStatus.service';
import {ImmobilieDTO} from '../models/immobilie.model';

@Component({
  selector: 'app-suche',
  imports: [
    FormsModule,
    AsyncPipe,
    ImmoUebersichtskarteComponent,
  ],
  templateUrl: './suche.component.html',
  standalone: true,
  styleUrl: './suche.component.scss',
})
export class SucheComponent implements OnInit {
  immobilienService = inject(ImmobilienService);
  statusService = inject(ImmoStatusService);

  stati$ = this.statusService.getAllStati();
  search = model<string>('');
  aktiverFilter = signal<string>('')

  searchedImmobilien = output<ImmobilieDTO[]>()

  immobilien$ = toObservable(this.search).pipe(
    startWith(''),
    distinctUntilChanged(),
    debounceTime(300),
    switchMap((search) => this.immobilienService.getImmobilienBySearchAndFilter(search, this.aktiverFilter())),
    tap(() => console.log('searched')),
  );

  ngOnInit(): void {
    this.immobilien$.subscribe(i => this.searchedImmobilien.emit(i));
  }

  doFilter(status: string):void {
    this.aktiverFilter.set(status);
    this.immobilien$ = this.immobilienService.getImmobilienBySearchAndFilter(this.search(), this.aktiverFilter());
  }

  resetFilter(): void {
    this.aktiverFilter.set('');
    this.immobilien$ = this.immobilienService.getImmobilienBySearchAndFilter(this.search(), this.aktiverFilter());
  }
}
