import {Component, ElementRef, inject, signal, viewChild} from '@angular/core';
import { LeafletModule } from '@bluehalo/ngx-leaflet';
import { latLng } from 'leaflet';
import {BehaviorSubject, map, tap} from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { Router } from '@angular/router';
import { SucheComponent } from '../suche/suche.component';
import { ImmobilieDTO } from '../models/immobilie.model';
import { ImmoUebersichtskarteComponent } from '../shared/immo-uebersichtskarte/immo-uebersichtskarte.component';
import { herneZentrum, makeMarker, osm, wikiM } from '../shared/cards-data';

@Component({
  selector: 'app-karte',
  imports: [
    LeafletModule,
    AsyncPipe,
    SucheComponent,
    ImmoUebersichtskarteComponent,
    ImmoUebersichtskarteComponent,
  ],
  templateUrl: './karte.component.html',
  styleUrl: './karte.component.scss',
  standalone: true,
})
export class KarteComponent {
  router = inject(Router);

  hoveredCard = signal<ImmobilieDTO | undefined>(undefined);

  immobilien$ = new BehaviorSubject<ImmobilieDTO[] | undefined>(undefined);
  chosenImmobilie = signal<ImmobilieDTO | undefined>(undefined);

  markers$ = this.immobilien$.pipe(
    map(i => {
      if (!i) return undefined;
      return i.map(i => {
        const m = makeMarker(i);
        m.on('click', () => {
          this.routeToImmobilie(i);
        });
        m.on('mouseover', () => {
          this.chosenImmobilie.set(i);
        });
        m.on('mouseout', () => {
         this.chosenImmobilie.set(undefined)
        })
        return {
          marker: m,
          entity: i,
        };
      });
    }),
  );

  layersControl = {
    baseLayers: {
      'Open Street Maps': osm,
      Wikimaps: wikiM,
    },
    overlays: {},
  };

  options = {
    layers: [osm],
    zoom: 12,
    center: latLng(herneZentrum.xKoordinate, herneZentrum.yKoordinate),
  };

  routeToImmobilie(i: ImmobilieDTO): void {
    this.router.navigateByUrl('immobilie/' + i.id);
  }
}
