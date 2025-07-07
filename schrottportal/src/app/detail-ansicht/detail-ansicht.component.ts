import {AfterViewInit, Component, input, OnInit} from '@angular/core';
import {ImmobilieDTO} from '../models/immobilie.model';
import {map, Observable, switchMap} from 'rxjs';
import {ImmobilienService} from '../services/immobilien.service';
import {AsyncPipe, NgClass} from '@angular/common';
import {AmpelSliderComponent} from './ampel-slider/ampel-slider.component';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';


@Component({
  selector: 'app-detail-ansicht',
  imports: [
    AsyncPipe,
    NgClass,
    AmpelSliderComponent,
    RouterLink
  ],
  templateUrl: './detail-ansicht.component.html',
  styleUrl: './detail-ansicht.component.scss'
})
export class DetailAnsichtComponent implements OnInit {
  id = input<number>();
  immobilie$!: Observable<ImmobilieDTO>;
  value: string | undefined;
  color: string | undefined;

  constructor(private immoService: ImmobilienService, private router:ActivatedRoute) {
  }


  possibleStatus: string[] = ["VERDACHT",
    "ANGEGUCKT",
    "ANHOERUNG",
    "WOHNUNGSAUFSICHT",
    "BESEITIGUNG",
    "NEUBAU",
    "NUTZUNG",
    "INSTANDSETZUNG",
    "ERWERB",
    "GELOEST"]

  ngOnInit(): void {
    this.immobilie$ = this.router.params.pipe(
      map(params=> (params as any)['id']as number),
      switchMap(id =>  this.immoService.getImmobilieById(id))
    )
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
}
