import {Component, inject, input, OnInit, signal} from '@angular/core';
import {LeafletModule} from '@bluehalo/ngx-leaflet';
import {latLng, marker, tileLayer} from 'leaflet';
import {BehaviorSubject, map} from 'rxjs';
import {AsyncPipe} from '@angular/common';
import {Router} from '@angular/router';

type Koordinaten = {
  xKoordinate: number,
  yKoordinate: number,
}

type Immobilie = {
  id: number,
  bezeichnung: string,
  koordinaten: Koordinaten
}

@Component({
  selector: 'app-karte',
  imports: [
    LeafletModule,
    AsyncPipe
  ],
  templateUrl: './karte.component.html',
  styleUrl: './karte.component.scss',
  standalone: true,
})
export class KarteComponent implements OnInit {
  router = inject(Router);
  herneZentrum: Koordinaten = {
    xKoordinate: 51.541944444444,
    yKoordinate: 7.223888888888965,
  }
  testKoordinaten: Koordinaten = {
    xKoordinate: 51.54,
    yKoordinate: 7.22,
  }
  testImmobilie = {id: 1, bezeichnung: 'testImmobilie', koordinaten: this.herneZentrum} as Immobilie;
  testImmobilie2 = {id: 2, bezeichnung: 'testImmobilie2', koordinaten: this.testKoordinaten} as Immobilie;
  immobilien = input.required<Immobilie[]>()
  // immobilien$ = toObservable(this.immobilien);
  immobilien$: BehaviorSubject<Immobilie[]> = new BehaviorSubject([
    {id: 1, bezeichnung: '', koordinaten: {xKoordinate: 51, yKoordinate: 7}}
  ]);
  chosenImmobilie = signal<Immobilie | undefined>(undefined);

  markers$ = this.immobilien$.pipe(map(i =>
    i.map(i => {
      let m = marker([i.koordinaten.xKoordinate, i.koordinaten.yKoordinate], {riseOnHover: true, title: i.bezeichnung, alt: i.bezeichnung});
      m.on('click', (event) => {
        console.log('mapped clickEvent');
        console.log(event);
        this.routeToImmobilie(i);
      });//todo: hovereffect (tooltip?)
      m.on('mouseover', (event) => {
        console.log('mapped hoverEvent');
        this.chosenImmobilie.set(i);
      });
      return m;
    })
  ));

  osm = tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {maxZoom: 18, attribution: '...'});
  wikiM = tileLayer('http://maps.wikimedia.org/osm-intl/{z}/{x}/{y}.png', {maxZoom: 18});

  testtext = signal('ungeklickt');



  // markers = [this.testmarker, marker([51.54, 7.224]), marker([51.542, 7.223])];

  layersControl = {
    baseLayers: {
      'Open Street Maps': this.osm,
      'Wikimaps': this.wikiM,
    },
    overlays: {}
  };

  options = {
    layers: [this.osm],
    zoom: 13,
    center: latLng(51.541944, 7.223889),
  };

  ngOnInit(): void {}

  routeToImmobilie(i: Immobilie): void {
    this.router.navigateByUrl('immobilie' + i.id);
  }
}
