import { Component } from '@angular/core';
import {ImmoUebersichtskarteComponent} from './immo-uebersichtskarte/immo-uebersichtskarte.component';

@Component({
  selector: 'app-uebersicht',
  imports: [
    ImmoUebersichtskarteComponent
  ],
  templateUrl: './uebersicht.component.html',
  styleUrl: './uebersicht.component.scss'
})
export class UebersichtComponent {

}
