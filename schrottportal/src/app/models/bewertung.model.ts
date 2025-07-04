import { Kriterium } from './kriterium.model';

export type Bewertung = {
  id: number;
  gewichtung: number;
  kriterium: Kriterium;
};
