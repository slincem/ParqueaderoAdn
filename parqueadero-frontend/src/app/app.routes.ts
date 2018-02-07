import { RouterModule, Routes } from '@angular/router';
import {ConsultarVehiculosComponent} from './components/vehiculos/consultar-vehiculos/consultar-vehiculos.component';

const app_routes: Routes = [
  { path: 'vehiculos', component: ConsultarVehiculosComponent },
  { path: '**', pathMatch: 'full', redirectTo: '' }
];

export const APP_ROUTING = RouterModule.forRoot(app_routes);
