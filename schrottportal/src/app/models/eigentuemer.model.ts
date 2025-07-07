import { Adresse } from './adresse.model';

export type Eigentuemer = {
  id: number;
  vorname: string;
  nachname: string;
  anschrift: Adresse;
  anmerkung: string;
};
