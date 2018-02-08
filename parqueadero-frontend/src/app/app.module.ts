import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {APP_ROUTING} from './app.routes';

import { AppComponent } from './app.component';
import { RegistrarVehiculoComponent } from './components/vehiculos/registrar-vehiculo/registrar-vehiculo.component';
import { ConsultarVehiculosComponent } from './components/vehiculos/consultar-vehiculos/consultar-vehiculos.component';

import {VehiculosService} from './services/vehiculos.service';

@NgModule({
  declarations: [
    AppComponent,
    RegistrarVehiculoComponent,
    ConsultarVehiculosComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    APP_ROUTING
  ],
  providers: [HttpClientModule, VehiculosService],
  bootstrap: [AppComponent]
})
export class AppModule { }
