import { Component, OnInit } from '@angular/core';
import { VehiculosService } from '../../../services/vehiculos.service';
import { VERSION, MatDialogRef, MatDialog, MatSnackBar, MatFormField } from '@angular/material';
import { ConfirmacionSalidaVehiculoComponent } from '../confirmacion-salida-vehiculo/confirmacion-salida-vehiculo.component';
import { FacturaSalidaVehiculoComponent } from '../factura-salida-vehiculo/factura-salida-vehiculo.component';

@Component({
  selector: 'app-consultar-vehiculos',
  templateUrl: './consultar-vehiculos.component.html',
  styles: []
})
export class ConsultarVehiculosComponent implements OnInit {

  vehiculosEnParqueadero: any[] = [];
  loading: boolean = true;
  duracionSnack = 5000;

  constructor(private _vehiculosService: VehiculosService, private dialog: MatDialog,
    private snackBar: MatSnackBar) {

  }

  registrarSalida(placa) {
    console.log(placa)
    let dialogRef = this.dialog.open(ConfirmacionSalidaVehiculoComponent, {
      height: '340px',
      width: '600px',
      data: { placa: placa }
    });

    dialogRef.afterClosed().subscribe(vehiculoFactura => {
      if(vehiculoFactura != undefined) {
        this.snackBar.open("Registro de salida exitosa", 'Cerrar', {
          duration: this.duracionSnack
        });
        this.ngOnInit();
        let dialogRef = this.dialog.open(FacturaSalidaVehiculoComponent, {
          height: '340px',
          width: '600px',
          data: { vehiculoFactura: vehiculoFactura }
        });
      }
    });
  }

  ngOnInit() {
    this._vehiculosService.getVehiculos().subscribe(vehiculosList => {
      console.log("Vehiculos", vehiculosList);
      this.vehiculosEnParqueadero = vehiculosList;
      this.loading = false;
    });
  }
}
