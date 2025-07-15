import {Component, inject} from '@angular/core';
import {map, Observable, switchMap} from 'rxjs';
import {ImmobilienService} from '../services/immobilien.service';
import {AsyncPipe, NgClass} from '@angular/common';
import {AmpelSliderComponent} from './ampel-slider/ampel-slider.component';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {SingleImmoMapComponent} from './single-immo-map/single-immo-map.component';
import {ImmobilieDTO} from '../models/immobilie.model';

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
export class DetailAnsichtComponent {
  router = inject(ActivatedRoute);
  immoService = inject(ImmobilienService);



  immobilie$: Observable<ImmobilieDTO> = this.router.params.pipe(
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

}
