import { Routes } from '@angular/router';
import {UebersichtComponent} from './uebersicht/uebersicht.component';
import {SucheComponent} from './suche/suche.component';
import {KarteComponent} from './karte/karte.component';
import {KalenderComponent} from './kalender/kalender.component';
import {ArchivComponent} from './archiv/archiv.component';
import {DetailAnsichtComponent} from './detail-ansicht/detail-ansicht.component';

export const routes: Routes = [
  { path: 'uebersicht', component: UebersichtComponent },
  { path: 'suche', component: SucheComponent },
  { path: 'karte', component: KarteComponent },
  { path: 'kalender', component: KalenderComponent },
  { path: 'archiv', component: ArchivComponent },
  { path: 'detail-ansicht/:id', component: DetailAnsichtComponent },
  //{ path: '**', component: UebersichtComponent },
];
