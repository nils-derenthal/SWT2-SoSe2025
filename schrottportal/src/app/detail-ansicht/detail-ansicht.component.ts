import {Component, inject} from '@angular/core';
import {map, Observable, switchMap} from 'rxjs';
import {ImmobilienService} from '../services/immobilien.service';
import {AsyncPipe, NgClass} from '@angular/common';
import {AmpelSliderComponent} from './ampel-slider/ampel-slider.component';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {SingleImmoMapComponent} from './single-immo-map/single-immo-map.component';
import {ImmobilieDTO} from '../models/immobilie.model';
import {Component, inject, OnInit} from '@angular/core';
import {combineLatest, map, shareReplay, switchMap} from 'rxjs';
import {ImmobilienService} from '../services/immobilien.service';
import {AsyncPipe, NgClass} from '@angular/common';
import {AmpelSliderComponent} from './ampel-slider/ampel-slider.component';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {SingleImmoMapComponent} from './single-immo-map/single-immo-map.component';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {ImmoStatus} from '../models/immoStatus.model';
import {Adresse} from '../models/adresse.model';


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
export class DetailAnsichtComponent {
  router = inject(ActivatedRoute);
  immoService = inject(ImmobilienService);
  fb = inject(FormBuilder)



  immobilie$: Observable<ImmobilieDTO> = this.router.params.pipe(
    map(params => (params as any)['id'] as number),
    switchMap(id => this.immoService.getImmobilieById(id)),
    shareReplay({bufferSize: 1, refCount: true})
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

  statusForm: FormGroup = this.fb.group({
    status: [this.possibleStatus[0], Validators.required],
    description: ['', [Validators.required, Validators.minLength(3)]]
  });

  immoForm: FormGroup = this.fb.group({
    adress: ['', [Validators.required]],
    state: ['', [Validators.required]],
  })

  ngOnInit(): void {
    this.immobilie$.subscribe(immo => {
      this.immoForm.patchValue({
        adress: `${immo.adresse.strasse} ${immo.adresse.hausnummer}`,
        state: immo.zustand
      })
    })

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
    this.immobilie$.pipe(
      map(immo => {
        return {
          id: null,
          status: this.statusForm.get('status')!.value,
          beschreibung: this.statusForm.get('description')!.value,
          immobilieId: immo.id
        } as ImmoStatus
      }),
      switchMap(status =>
        this.immoService.addImmoStatus(status)
      )
    ).subscribe(() =>
      window.location.reload()
    )
  }

  setStatusActive(statusId: number) {
    this.immobilie$.subscribe(immo => {
      this.immoService.setStatusActive(statusId!, immo.id).subscribe(() =>
        window.location.reload()
      );
    })
  }

  archive(id: number, value: boolean) {
    this.immoService.archive(id, value).subscribe(() =>
      window.location.reload()
    );
  }

  onSubmit(id: number) {
    this.immoForm.markAsPristine()
    const adresseRaw = this.immoForm.get('adress')?.value
    const adresse = {
      strasse: adresseRaw.split(' ')[0],
      hausnummer: adresseRaw.split(' ')[1],
    } as Adresse

    combineLatest(
      [this.immoService.setZustand(id, this.immoForm.get('state')?.value),
        this.immoService.setAdresse(id, adresse)]
    )
      .subscribe(() =>
        window.location.reload())
  }
}
