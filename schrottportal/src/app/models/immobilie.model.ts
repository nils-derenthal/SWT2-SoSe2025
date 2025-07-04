import {Adresse} from './adresse.model';
import {Koordinaten} from './koordinaten.model';
import {Bewertung} from './bewertung.model';
import {Eigentuemer} from './eigentuemer.model';
import {ImmoStatus} from './immoStatus.model';

export type ImmobilieDTO = {
  id: number;
  adresse: Adresse;
  bezeichnung: string;
  archiviert: boolean;
  zustand: string;
  koordinaten: Koordinaten;
  gemarkung: string;
  flur: string;
  flurstueck: string;
  quadratMeter: number;
  gebaeudetyp: string;
  eigentumsform: string;
  bewertungen: Bewertung[];
  eigentuemer: Eigentuemer;
  immoStati: ImmoStatus[];
  aktuellerStatusId: number
}
