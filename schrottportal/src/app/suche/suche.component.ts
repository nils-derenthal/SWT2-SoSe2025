import {Component, inject, OnInit, signal} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {ImmobilienService} from '../services/immobilien.service';
import {toObservable} from '@angular/core/rxjs-interop';
import {debounceTime, distinctUntilChanged, Observable, startWith, switchMap} from 'rxjs';
import {AsyncPipe} from '@angular/common';
import {ImmoUebersichtskarteComponent} from '../uebersicht/immo-uebersichtskarte/immo-uebersichtskarte.component';
import {ImmoStatusService} from '../services/immoStatus.service';

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
  stati$!: Observable<string[]>;
  aktiverFilter = signal<string>('')
  search = signal<string>('');


  ngOnInit(): void {
    this.getStati();
  }
  immobilien$ = toObservable((this.search)).pipe(
    startWith(''),
    distinctUntilChanged(),
    debounceTime(300),
    switchMap((search) => this.immobilienService.getImmobilienBySearchAndFilter(search, this.aktiverFilter())),
  );

  getStati(): void {
    this.stati$ = this.statusService.getAllStati();
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
