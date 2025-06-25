import {Component, OnInit, signal} from '@angular/core';
import {LeafletModule} from '@bluehalo/ngx-leaflet';
import {latLng, marker, tileLayer} from 'leaflet';

@Component({
  selector: 'app-karte',
  imports: [
    LeafletModule
  ],
  templateUrl: './karte.component.html',
  styleUrl: './karte.component.scss',
})
export class KarteComponent implements OnInit{
  osm = tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {maxZoom: 18, attribution: '...'});
  wikiM = tileLayer('http://maps.wikimedia.org/osm-intl/{z}/{x}/{y}.png', {maxZoom: 18});

  testtext = signal('ungeklickt');

  testmarker = marker([51.541944, 7.223889], {riseOnHover: true, title: this.testtext()});


  markers = [this.testmarker, marker([51.5412, 7.2239]), marker([51.5419, 7.2238])];

  layersControl = {
    baseLayers: {
      'Open Street Maps': this.osm,
      'Wikimaps': this.wikiM,
    },
    overlays: {}
  };

  options = {
    layers: [this.osm, ...this.markers],
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
