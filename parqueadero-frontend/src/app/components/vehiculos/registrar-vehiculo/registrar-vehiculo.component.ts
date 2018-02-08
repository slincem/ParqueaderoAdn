import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Vehiculo } from '../../../interfaces/vehiculo.interface';
import { VehiculosService } from '../../../services/vehiculos.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-registrar-vehiculo',
  templateUrl: './registrar-vehiculo.component.html',
  styles: []
})
export class RegistrarVehiculoComponent implements OnInit {

  duracionSnack = 5000;
  formulario: FormGroup;
  vehiculo: Vehiculo = {
    placa: "",
    cilindraje: "",
    tipoVehiculo: "Carro"
  }

  constructor(private _vehiculosService: VehiculosService,
    private snackBar: MatSnackBar) {
  }

  registrarVehiculo() {

    this._vehiculosService.registrarVehiculo(this.vehiculo).subscribe(respuesta => {
      this.snackBar.open("Registro exitoso", 'Cerrar', {
        duration: this.duracionSnack
      });
      this.formulario.reset({ tipoVehiculo: "Carro" });
    }, error => this.snackBar.open(error.error, 'Cerrar', {
      duration: this.duracionSnack
    }));

  }

  nuevoVehiculo() {
    this.formulario.reset({ tipoVehiculo: "Carro" });
  }

  ngOnInit() {
    this.formulario = new FormGroup({
      'placa': new FormControl('',
        [Validators.required,
        Validators.pattern("[a-zA-Z0-9]+$"),
        Validators.minLength(6)]),

      'cilindraje': new FormControl('', [Validators.required,
      Validators.pattern("[0-9]+$"),
      Validators.maxLength(5)]),

      'tipoVehiculo': new FormControl('', Validators.required)
    });

    this.formulario.setValue(this.vehiculo);
    console.log("vehiculoform", this.vehiculo)
  }

}
