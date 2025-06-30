import { Component, OnInit } from '@angular/core';
import { AmpelSliderComponent } from '../detailansicht/ampel-slider/ampel-slider.component';
import { Observable } from 'rxjs';
import { ImmobilieDTO } from '../models/immobilie.model';
import { ImmobilienService } from '../services/immobilien.service';
import { AsyncPipe } from '@angular/common';
import { ImmoUebersichtskarteComponent } from './immo-uebersichtskarte/immo-uebersichtskarte.component';

@Component({
  selector: 'app-uebersicht',
  imports: [AsyncPipe, ImmoUebersichtskarteComponent],
  templateUrl: './uebersicht.component.html',
  styleUrl: './uebersicht.component.scss',
  standalone: true,
})
export class UebersichtComponent implements OnInit {
  immobilien$!: Observable<ImmobilieDTO[]>;

  constructor(private immoService: ImmobilienService) {}

  ngOnInit(): void {
    this.getAllImmobilien();
  }

  getAllImmobilien() {
    this.immobilien$ = this.immoService.getAllImmobilien();
  }
}
