import { Component, Input } from '@angular/core';
import { ImmobilieDTO } from '../../models/immobilie.model';
import {NgOptimizedImage} from "@angular/common";
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-immo-uebersichtskarte',
  imports: [
    NgOptimizedImage,
    RouterLink
  ],
  templateUrl: './immo-uebersichtskarte.component.html',
  styleUrl: './immo-uebersichtskarte.component.scss',
})
export class ImmoUebersichtskarteComponent {
  @Input() immobilie!: ImmobilieDTO;
}
