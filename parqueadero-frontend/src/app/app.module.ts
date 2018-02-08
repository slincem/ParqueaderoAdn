import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { MatSnackBarModule, MatDialogModule, MatButtonModule, MatFormFieldModule } from '@angular/material';

import { APP_ROUTING } from './app.routes';

import { AppComponent } from './app.component';
import { RegistrarVehiculoComponent } from './components/vehiculos/registrar-vehiculo/registrar-vehiculo.component';
import { ConsultarVehiculosComponent } from './components/vehiculos/consultar-vehiculos/consultar-vehiculos.component';

import { VehiculosService } from './services/vehiculos.service';
import { ConfirmacionSalidaVehiculoComponent } from './components/vehiculos/confirmacion-salida-vehiculo/confirmacion-salida-vehiculo.component';
import { FacturaSalidaVehiculoComponent } from './components/vehiculos/factura-salida-vehiculo/factura-salida-vehiculo.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrarVehiculoComponent,
    ConsultarVehiculosComponent,
    ConfirmacionSalidaVehiculoComponent,
    FacturaSalidaVehiculoComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    MatDialogModule,
    MatButtonModule,
    MatFormFieldModule,
    APP_ROUTING
  ],
  providers: [HttpClientModule, VehiculosService],
  bootstrap: [AppComponent],
  entryComponents: [ConfirmacionSalidaVehiculoComponent, FacturaSalidaVehiculoComponent]
})
export class AppModule { }
