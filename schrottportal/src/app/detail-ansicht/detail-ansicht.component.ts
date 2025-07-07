import {Component, input, Input} from '@angular/core';
import {ImmobilieDTO} from '../models/immobilie.model';
import { Observable } from 'rxjs';
import {ImmobilienService} from '../services/immobilien.service';
import {AsyncPipe, NgClass} from '@angular/common';
import {Adresse} from '../models/adresse.model';
import {Koordinaten} from '../models/koordinaten.model';
import {Bewertung} from '../models/bewertung.model';
import {Eigentuemer} from '../models/eigentuemer.model';
import {ImmoStatus} from '../models/immoStatus.model';

@Component({
  selector: 'app-detail-ansicht',
  imports: [
    AsyncPipe,
    NgClass
  ],
  templateUrl: './detail-ansicht.component.html',
  styleUrl: './detail-ansicht.component.scss'
})
export class DetailAnsichtComponent {
  id = input.required<number>();
  immobilie$!: Observable<ImmobilieDTO>;
  value: string | undefined;
  color: string | undefined;

  constructor(private immoService: ImmobilienService) {
  }

  ngOnInit(): void {
    this.getImmobilieById();
    this.value = "0";

    switch (this.value) {
      case '0':
        this.color = 'red';
        break;

      case '1':
        this.color = 'orange';
        break;

      case '2':
        this.color = 'yellow';
        break;

      case '3':
        this.color = 'green';
        break;

      case '4':
        this.color = 'blue';
        break;
    }
  }

  getImmobilieById() {
    this.immobilie$ = this.immoService.getImmobilieById(this.id());
  }


  adresse: Adresse = {
    id: 1,
    strasse: "str",
    hausnummer: 4,
    hausnummerZusatz: "",
    plz: 3333,
    ort: "ort",
    stadtbezirk: "bezirk"
  }

  eigentuemer: Eigentuemer = {
    id: 3,
    vorname: "Peter",
    nachname: "Peter",
    anschrift: this.adresse,
    anmerkung: ""
  }

  status1: ImmoStatus = {
    id: 1,
    status: "status1",
    beschreibung: "beschreibung1",
    immobilieId: 1
  }

  status2: ImmoStatus = {
    id: 2,
    status: "status2",
    beschreibung: "beschreibung2",
    immobilieId: 1
  }

  status3: ImmoStatus = {
    id: 3,
    status: "status3",
    beschreibung: "beschreibung3",
    immobilieId: 1
  }

  status4: ImmoStatus = {
    id: 4,
    status: "status4",
    beschreibung: "beschreibung4",
    immobilieId: 1
  }

  status5: ImmoStatus = {
    id: 5,
    status: "status5",
    beschreibung: "beschreibung5",
    immobilieId: 1
  }

  @Input() immobilie: ImmobilieDTO = {
    id: 1,
    adresse: this.adresse,
    bezeichnung: "bezeichnung",
    archiviert: false,
    zustand: "zustand",
    koordinaten: {xKoordinate: 3, yKoordinate: 4, id: 9},
    gemarkung: "gemarkung",
    flur: "flur",
    flurstueck: "flurstueck",
    quadratMeter: 40,
    gebaeudetyp: "typ",
    eigentumsform: "eigentumsform",
    bewertungen: [],
    eigentuemer: this.eigentuemer,
    immoStati: [this.status1, this.status2, this.status3, this.status4, this.status4, this.status5],
    aktuellerStatusId: 3
  };


}
