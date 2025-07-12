import { Component } from '@angular/core';
import {Observable} from "rxjs";
import {ImmobilieDTO} from "../models/immobilie.model";
import {ImmobilienService} from "../services/immobilien.service";
import {AsyncPipe} from "@angular/common";
import {ImmoUebersichtskarteComponent} from "../shared/immo-uebersichtskarte/immo-uebersichtskarte.component";

@Component({
  selector: 'app-archiv',
  imports: [
    AsyncPipe,
    ImmoUebersichtskarteComponent
  ],
  templateUrl: './archiv.component.html',
  standalone: true,
  styleUrl: './archiv.component.scss'
})
export class ArchivComponent {
  immobilien$!: Observable<ImmobilieDTO[]>;

  constructor(private immoService: ImmobilienService) {}

  ngOnInit(): void {
    this.getArchivedImmobilien();
  }

  getArchivedImmobilien() {
    this.immobilien$ = this.immoService.getArchivedImmobilien();
  }
}