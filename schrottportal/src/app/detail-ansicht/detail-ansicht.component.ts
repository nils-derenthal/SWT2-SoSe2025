import { Component, inject, OnInit } from '@angular/core';
import { map, switchMap } from 'rxjs';
import { ImmobilienService } from '../services/immobilien.service';
import { AsyncPipe, NgClass } from '@angular/common';
import { AmpelSliderComponent } from './ampel-slider/ampel-slider.component';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { SingleImmoMapComponent } from './single-immo-map/single-immo-map.component';

@Component({
  selector: 'app-detail-ansicht',
  imports: [
    AsyncPipe,
    NgClass,
    AmpelSliderComponent,
    RouterLink,
    SingleImmoMapComponent,
  ],
  templateUrl: './detail-ansicht.component.html',
  styleUrl: './detail-ansicht.component.scss',
})
export class DetailAnsichtComponent implements OnInit {
  router = inject(ActivatedRoute);
  immoService = inject(ImmobilienService);

  immobilie$ = this.router.params.pipe(
    map(params => (params as any)['id'] as number),
    switchMap(id => this.immoService.getImmobilieById(id)),
  );

  value: string | undefined;
  color: string | undefined;

  possibleStatus: string[] = [
    'VERDACHT',
    'ANGEGUCKT',
    'ANHOERUNG',
    'WOHNUNGSAUFSICHT',
    'BESEITIGUNG',
    'NEUBAU',
    'NUTZUNG',
    'INSTANDSETZUNG',
    'ERWERB',
    'GELOEST',
  ];

  ngOnInit(): void {
    this.value = '0';

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
