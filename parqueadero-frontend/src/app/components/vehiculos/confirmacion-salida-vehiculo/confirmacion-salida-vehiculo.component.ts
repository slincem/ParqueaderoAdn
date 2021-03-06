import { Component, OnInit, Inject } from '@angular/core';
import { VehiculosService } from '../../../services/vehiculos.service';
import { VERSION, MatDialogRef, MatDialog, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-confirmacion-salida-vehiculo',
  templateUrl: './confirmacion-salida-vehiculo.component.html',
  styles: []
})
export class ConfirmacionSalidaVehiculoComponent {

  vehiculoConsultado: any;
  duracionSnack = 5000;

  constructor(public dialogRef:
    MatDialogRef<ConfirmacionSalidaVehiculoComponent>, @Inject(MAT_DIALOG_DATA) private data: any,
    private _vehiculosService: VehiculosService, private snackBar: MatSnackBar) {

    this._vehiculosService.getVehiculo(data.placa).subscribe(vehiculosConsultado => {
      this.vehiculoConsultado = vehiculosConsultado;

    });
  }

  registrarSalida() {
    this._vehiculosService.registrarSalidaVehiculo(this.data.placa).subscribe(respuesta => {
      this.vehiculoConsultado.valorAPagar = respuesta;
      this.dialogRef.close(this.vehiculoConsultado);
    }, error => {
      this.snackBar.open(error.error, 'Cerrar', {
        duration: this.duracionSnack
      });
    });
  }

  cancelar() {
    this.dialogRef.close(undefined);
  }

}
