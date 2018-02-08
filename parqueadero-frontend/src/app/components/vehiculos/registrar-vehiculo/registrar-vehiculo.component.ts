import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import {Vehiculo} from '../../../interfaces/vehiculo.interface';
import {VehiculosService} from '../../../services/vehiculos.service';

@Component({
  selector: 'app-registrar-vehiculo',
  templateUrl: './registrar-vehiculo.component.html',
  styles: []
})
export class RegistrarVehiculoComponent implements OnInit {

  formulario: FormGroup;
  vehiculo: Vehiculo = {
    placa : "",
    cilindraje : "",
    tipoVehiculo : "Carro"
  }

  constructor(private _vehiculosService: VehiculosService) {
  }

  registrarVehiculo() {

    console.log("vehiculoOk", this.vehiculo);

    this._vehiculosService.registrarVehiculo(this.vehiculo).subscribe(respuesta => {
      console.log(respuesta);
      this.formulario.reset({tipoVehiculo:"Carro"});
    } , error => console.log("Error", error));

  }

  nuevoVehiculo() {
    this.formulario.reset({tipoVehiculo:"Carro"});
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
