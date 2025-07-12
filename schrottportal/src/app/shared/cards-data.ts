import { DivIcon, marker, tileLayer } from 'leaflet';
import { ImmobilieDTO } from '../models/immobilie.model';
import { KoordinatenDTO } from '../models/koordinaten.model';

export const osm = tileLayer(
  'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
  {
    maxZoom: 18,
  },
);

export const wikiM = tileLayer(
  'http://maps.wikimedia.org/osm-intl/{z}/{x}/{y}.png',
  {
    maxZoom: 18,
  },
);

export const bootstrapMarkerIcon: DivIcon = new DivIcon({
  className: 'bi bi-geo-alt-fill text-danger h3',
  iconAnchor: [14, 30],
});

export const herneZentrum: KoordinatenDTO = {
  xKoordinate: 51.541944444444,
  yKoordinate: 7.223888888888965,
};

export function makeMarker(i: ImmobilieDTO) {
  return marker([i.koordinaten.xKoordinate, i.koordinaten.yKoordinate], {
    // icon: bootstrapMarkerIcon,
    riseOnHover: true,
    title: i.bezeichnung,
    alt: i.bezeichnung,
  });
}
