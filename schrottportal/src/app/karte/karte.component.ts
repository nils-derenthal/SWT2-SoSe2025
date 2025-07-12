import {Component, ElementRef, inject, signal, viewChild} from '@angular/core';
import { LeafletModule } from '@bluehalo/ngx-leaflet';
import { DivIcon, latLng, marker, tileLayer } from 'leaflet';
import {BehaviorSubject, map, tap} from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { Router } from '@angular/router';
import 'leaflet/dist/images/marker-shadow.png';
import { SucheComponent } from '../suche/suche.component';
import { KoordinatenDTO } from '../models/koordinaten.model';
import { ImmobilieDTO } from '../models/immobilie.model';
import { ImmoUebersichtskarteComponent } from '../uebersicht/immo-uebersichtskarte/immo-uebersichtskarte.component';

@Component({
  selector: 'app-karte',
  imports: [
    LeafletModule,
    AsyncPipe,
    SucheComponent,
    ImmoUebersichtskarteComponent,
  ],
  templateUrl: './karte.component.html',
  styleUrl: './karte.component.scss',
  standalone: true,
})
export class KarteComponent {
  router = inject(Router);
  herneZentrum: KoordinatenDTO = {
    xKoordinate: 51.541944444444,
    yKoordinate: 7.223888888888965,
  };
  bootstrapMarkerIcon: DivIcon = new DivIcon({
    className: 'bi bi-geo-alt-fill text-danger h3',
    iconAnchor: [14, 30],
  });

  hoveredCard = signal<ImmobilieDTO | undefined>(undefined);

  immobilien$ = new BehaviorSubject<ImmobilieDTO[] | undefined>(undefined);
  chosenImmobilie = signal<ImmobilieDTO | undefined>(undefined);

  markers$ = this.immobilien$.pipe(
    map(i => {
      if (!i) return undefined;
      return i.map(i => {
        const m = marker(
          [i.koordinaten.xKoordinate, i.koordinaten.yKoordinate],
          {
            // icon: this.bootstrapMarkerIcon,
            riseOnHover: true,
            title: i.bezeichnung,
            alt: i.bezeichnung,
          },
        );

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

  osm = tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 18,
    attribution: '...',
  });
  wikiM = tileLayer('http://maps.wikimedia.org/osm-intl/{z}/{x}/{y}.png', {
    maxZoom: 18,
  });

  layersControl = {
    baseLayers: {
      'Open Street Maps': this.osm,
      Wikimaps: this.wikiM,
    },
    overlays: {},
  };

  options = {
    layers: [this.osm],
    zoom: 12,
    center: latLng(
      this.herneZentrum.xKoordinate,
      this.herneZentrum.yKoordinate,
    ),
  };

  routeToImmobilie(i: ImmobilieDTO): void {
    this.router.navigateByUrl('immobilie/' + i.id);
  }
}
