import { Routes } from '@angular/router';
import { UebersichtComponent } from './uebersicht/uebersicht.component';
import { SucheComponent } from './suche/suche.component';
import { KarteComponent } from './karte/karte.component';
import { ArchivComponent } from './archiv/archiv.component';
import { LoginComponent } from "./login/login.component";
import { DetailAnsichtComponent } from './detail-ansicht/detail-ansicht.component';

export const routes: Routes = [
  { path: 'uebersicht', component: UebersichtComponent },
  { path: 'suche', component: SucheComponent },
  { path: 'karte', component: KarteComponent },
  { path: 'archiv', component: ArchivComponent },
  { path: 'detail-ansicht/:id', component: DetailAnsichtComponent },
  { path: 'login', component: LoginComponent },
  { path: '**', redirectTo: 'uebersicht' },
];
