import {
  Component,
  inject,
  model,
  OnInit,
  output,
  signal,
} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ImmobilienService } from '../services/immobilien.service';
import { toObservable } from '@angular/core/rxjs-interop';
import {
  combineLatest,
  debounceTime,
  distinctUntilChanged,
  filter,
  shareReplay,
  startWith,
  switchMap,
} from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { ImmobilieDTO } from '../models/immobilie.model';
import { ImmoUebersichtskarteComponent } from '../shared/immo-uebersichtskarte/immo-uebersichtskarte.component';
import { ImmoStatusService } from '../services/immoStatus.service';

@Component({
  selector: 'app-suche',
  imports: [FormsModule, AsyncPipe, ImmoUebersichtskarteComponent],
  templateUrl: './suche.component.html',
  standalone: true,
  styleUrl: './suche.component.scss',
})
export class SucheComponent implements OnInit {
  immobilienService = inject(ImmobilienService);
  statusService = inject(ImmoStatusService);
  stati$ = this.statusService.getAllStati();
  search = model<string>('');
  aktiverFilter = signal<string>('');

  searchedImmobilien = output<ImmobilieDTO[]>();

  immobilien$ = combineLatest([
    toObservable(this.search),
    toObservable(this.aktiverFilter),
  ]).pipe(
    filter(([search]) => search !== undefined),
    distinctUntilChanged(),
    debounceTime(300),
    startWith<[string, string]>(['', '']),
    switchMap(([search, filter]) =>
      this.immobilienService.getImmobilienBySearchAndFilter(search, filter),
    ),
    shareReplay({ refCount: true, bufferSize: 1 }),
  );

  ngOnInit(): void {
    this.immobilien$.subscribe(i => this.searchedImmobilien.emit(i));
  }

  doFilter(status: string): void {
    this.aktiverFilter.set(status);
  }

  resetFilter(): void {
    this.aktiverFilter.set('');
  }
}
