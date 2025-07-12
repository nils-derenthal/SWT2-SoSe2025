import {Component, inject, OnInit} from '@angular/core';
import {map, shareReplay, switchMap} from 'rxjs';
import {ImmobilienService} from '../services/immobilien.service';
import {AsyncPipe, NgClass} from '@angular/common';
import {AmpelSliderComponent} from './ampel-slider/ampel-slider.component';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {SingleImmoMapComponent} from './single-immo-map/single-immo-map.component';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {ImmoStatus} from '../models/immoStatus.model';


@Component({
  selector: 'app-detail-ansicht',
  imports: [
    AsyncPipe,
    NgClass,
    AmpelSliderComponent,
    RouterLink,
    ReactiveFormsModule,
    SingleImmoMapComponent,
  ],
  templateUrl: './detail-ansicht.component.html',
  styleUrl: './detail-ansicht.component.scss',
})
export class DetailAnsichtComponent implements OnInit {
  router = inject(ActivatedRoute);
  immoService = inject(ImmobilienService);
  fb = inject(FormBuilder)

  immobilie$ = this.router.params.pipe(
    map(params => (params as any)['id'] as number),
    switchMap(id => this.immoService.getImmobilieById(id)),
    shareReplay({bufferSize:1, refCount: true})
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

  form :FormGroup = this.fb.group({
    status: [this.possibleStatus[0], Validators.required],
    description: ['', [Validators.required, Validators.minLength(3)]]
  });

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

  addStatus() {
    this.immobilie$.subscribe(immo=>{
      const status:ImmoStatus = {
        id: null,
        status: this.form.get('status')!.value,
        beschreibung: this.form.get('description')!.value,
        immobilieId: immo.id
      }

      this.immoService.addImmoStatus(status).subscribe()
    })


  }

  setStatusActive(statusId: number) {
    this.immobilie$.subscribe(immo=>{
      this.immoService.setStatusActive(statusId!, immo.id).subscribe();
    })
    }
}
