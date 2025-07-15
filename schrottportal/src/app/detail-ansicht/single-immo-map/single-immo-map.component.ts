import { Component, computed, input } from '@angular/core';
import { LeafletModule } from '@bluehalo/ngx-leaflet';
import { latLng } from 'leaflet';
import { makeMarker, osm, wikiM } from '../../shared/cards-data';
import { ImmobilieDTO } from '../../models/immobilie.model';

@Component({
  selector: 'app-single-immo-map',
  imports: [LeafletModule],
  templateUrl: './single-immo-map.component.html',
  styleUrl: './single-immo-map.component.scss',
  standalone: true
})
export class SingleImmoMapComponent {
  immobilie = input.required<ImmobilieDTO>();

  layersControl = {
    baseLayers: {
      'Open Street Maps': osm,
      Wikimaps: wikiM,
    },
    overlays: {},
  };

  options = computed(() => ({
    layers: [osm, makeMarker(this.immobilie())],
    zoom: 13,
    center: latLng(
      this.immobilie().koordinaten.xKoordinate,
      this.immobilie().koordinaten.yKoordinate,
    ),
  }));
}
