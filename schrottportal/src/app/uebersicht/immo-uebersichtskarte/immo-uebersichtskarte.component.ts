import { Component } from '@angular/core';
import {StatusService} from '../service/statusService';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-immo-uebersichtskarte',
  imports: [],
  templateUrl: './immo-uebersichtskarte.component.html',
  styleUrl: './immo-uebersichtskarte.component.scss'
})
export class ImmoUebersichtskarteComponent {


  constructor(private statusService: StatusService) {
  }

  getStatus(immoId: number): Observable<void>{
    return this.statusService.getImmoStatus$(immoId);
  }

}

