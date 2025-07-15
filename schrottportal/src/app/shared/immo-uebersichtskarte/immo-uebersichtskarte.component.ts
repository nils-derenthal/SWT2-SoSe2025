import {Component, Input, OnInit} from '@angular/core';
import {ImmobilieDTO} from '../../models/immobilie.model';
import {RouterLink} from '@angular/router';
import {map} from 'rxjs';
import {LowerCasePipe} from '@angular/common';

@Component({
  selector: 'app-immo-uebersichtskarte',
  imports: [
    RouterLink,
    LowerCasePipe
  ],
  templateUrl: './immo-uebersichtskarte.component.html',
  styleUrl: './immo-uebersichtskarte.component.scss',
  standalone: true
})
export class ImmoUebersichtskarteComponent implements OnInit {
  @Input() immobilie!: ImmobilieDTO;
  protected readonly map = map;
  activeState: string = '';

  ngOnInit(): void {
    this.immobilie.immoStati.forEach(value => {
      if (value.id === this.immobilie.aktuellerStatusId) {
        this.activeState = value.status;
      }
    })
  }


}
