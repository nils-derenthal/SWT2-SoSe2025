import {Component, input, OnInit, signal} from '@angular/core';
import {LeafletModule} from '@bluehalo/ngx-leaflet';
import {latLng, marker, tileLayer} from 'leaflet';
import {BehaviorSubject, map} from 'rxjs';
import {AsyncPipe} from '@angular/common';

type Immobilie = {
  id: number,
  bezeichnung: string,
  koordinaten: {
    xKoordinate: number,
    yKoordinate: number,
  }
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
  testImmobilie = {id: 1, bezeichnung: '', koordinaten: {xKoordinate: 51.541944, yKoordinate: 7.223889}} as Immobilie;
  immobilien = input.required<Immobilie[]>()
  // immobilien$ = toObservable(this.immobilien);
  immobilien$: BehaviorSubject<Immobilie[]> = new BehaviorSubject([{id: 1, bezeichnung: '', koordinaten: {xKoordinate: 51, yKoordinate: 7}}]);

  markers$ = this.immobilien$.pipe(map(i =>
    i.map(i => {
      let m = marker([i.koordinaten.xKoordinate, i.koordinaten.yKoordinate], {riseOnHover: true, title: i.bezeichnung});
      m.on('click', (event) => {
        event.containerPoint.y;
      });
      return m;
    })
  ));

  osm = tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {maxZoom: 18, attribution: '...'});
  wikiM = tileLayer('http://maps.wikimedia.org/osm-intl/{z}/{x}/{y}.png', {maxZoom: 18});

  testtext = signal('ungeklickt');

  testmarker = marker([51.541944, 7.223889], {riseOnHover: true, title: this.testtext()});


  // markers = [this.testmarker, marker([51.54, 7.224]), marker([51.542, 7.223])];
  markers = [];

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
    center: latLng(51.541944, 7.223889)
  };

  ngOnInit(): void {
    this.testmarker.on('click', (event) => {
      console.log('ksdfl');
      this.testtext.set('geklickt');
    })
  }
}
