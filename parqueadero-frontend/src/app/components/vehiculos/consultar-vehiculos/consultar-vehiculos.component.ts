import { Component, OnInit } from '@angular/core';
import {VehiculosService} from '../../../services/vehiculos.service';

@Component({
  selector: 'app-consultar-vehiculos',
  templateUrl: './consultar-vehiculos.component.html',
  styles: []
})
export class ConsultarVehiculosComponent implements OnInit {

  vehiculosEnParqueadero: any[] = [];
  loading: boolean = true;

  constructor(private _vehiculosService: VehiculosService) {
    console.log("...");
    this._vehiculosService.getVehiculos().subscribe(vehiculosList => {
      console.log("Vehiculos", vehiculosList);
      this.vehiculosEnParqueadero = vehiculosList;
      this.loading = false;
    });
  }

  ngOnInit() {
  }
}
