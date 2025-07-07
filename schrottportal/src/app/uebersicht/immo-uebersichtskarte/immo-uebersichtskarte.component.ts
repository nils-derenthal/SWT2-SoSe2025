import { Component, Input } from '@angular/core';
import { ImmobilieDTO } from '../../models/immobilie.model';

@Component({
  selector: 'app-immo-uebersichtskarte',
  imports: [],
  templateUrl: './immo-uebersichtskarte.component.html',
  styleUrl: './immo-uebersichtskarte.component.scss',
})
export class ImmoUebersichtskarteComponent {
  @Input() immobilie!: ImmobilieDTO;
}
