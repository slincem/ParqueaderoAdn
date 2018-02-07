import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { RegistrarVehiculoComponent } from './components/vehiculos/registrar-vehiculo/registrar-vehiculo.component';
import { ConsultarVehiculosComponent } from './components/vehiculos/consultar-vehiculos/consultar-vehiculos.component';


@NgModule({
  declarations: [
    AppComponent,
    RegistrarVehiculoComponent,
    ConsultarVehiculosComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
