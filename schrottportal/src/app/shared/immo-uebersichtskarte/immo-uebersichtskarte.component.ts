import {Component, Input} from '@angular/core';
import {ImmobilieDTO} from '../../models/immobilie.model';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-immo-uebersichtskarte',
  imports: [
    RouterLink
  ],
  templateUrl: './immo-uebersichtskarte.component.html',
  styleUrl: './immo-uebersichtskarte.component.scss',
  standalone: true
})
export class ImmoUebersichtskarteComponent {
  @Input() immobilie!: ImmobilieDTO;
}
