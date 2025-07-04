import {Component, OnInit} from '@angular/core';
import {StatusService} from '../service/statusService';

@Component({
  selector: 'app-immo-uebersichtskarte',
  imports: [],
  templateUrl: './immo-uebersichtskarte.component.html',
  styleUrl: './immo-uebersichtskarte.component.scss'
})
export class ImmoUebersichtskarteComponent implements OnInit {


  constructor(private statusService: StatusService) {
  }

  ngOnInit(): void {

    }

  getStatus(immoId: number): void {
    this.statusService.getImmoStatus$(immoId);
  }

}

