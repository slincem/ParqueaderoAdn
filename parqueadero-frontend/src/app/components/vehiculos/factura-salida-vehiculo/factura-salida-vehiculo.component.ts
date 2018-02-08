import { Component, OnInit, Inject } from '@angular/core';
import { VehiculosService } from '../../../services/vehiculos.service';
import { VERSION, MatDialogRef, MatDialog, MatSnackBar, MatFormField, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-factura-salida-vehiculo',
  templateUrl: './factura-salida-vehiculo.component.html',
  styles: []
})
export class FacturaSalidaVehiculoComponent implements OnInit {

  vehiculoSalida:any;

  constructor(public dialogRef:
    MatDialogRef<FacturaSalidaVehiculoComponent>, @Inject(MAT_DIALOG_DATA) private data: any) {
      this.vehiculoSalida = data.vehiculoFactura;
      console.log("VehiculoFactura", this.vehiculoSalida);
    }

  ngOnInit() {
  }

}
