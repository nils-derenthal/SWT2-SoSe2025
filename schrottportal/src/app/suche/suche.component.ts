import {Component, inject, model, OnInit} from '@angular/core';
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

  ngOnInit(): void {
    this.getStati();
  }

  search = model<string>('');
  immobilien$ = toObservable(this.search).pipe(
    startWith(''),
    distinctUntilChanged(),
    debounceTime(300),
    switchMap((search) => this.immobilienService.getImmobilienBySearch(search)),
  );

  getStati(): void {
    this.stati$ = this.statusService.getAllStati();
  }

  doFilter(event: any):void {
    console.log(event.valueOf())
  }
}
