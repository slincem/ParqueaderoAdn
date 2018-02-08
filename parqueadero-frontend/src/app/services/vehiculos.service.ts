import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Vehiculo } from '../interfaces/vehiculo.interface';
import 'rxjs/Rx';

@Injectable()
export class VehiculosService {

  vehiculosURL: string = "http://localhost:8080/parqueadero/listarVehiculos";
  buscarVehiculoURL: string = "http://localhost:8080/parqueadero/buscarVehiculo";
  registroVehiculoURL: string = "http://localhost:8080/parqueadero/registrarEntradaVehiculo";
  saidaVehiculoURL: string = "http://localhost:8080/parqueadero/registrarSalidaVehiculo"

  constructor(public http: HttpClient) { }

  getVehiculos() {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.get(this.vehiculosURL, { headers }).map(
      (res: any) => {
        return res;
      }
    );
  }

  getVehiculo(placa) {
    let urlBusquedaVehiculo = `${this.buscarVehiculoURL}/${placa}`;
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.get(urlBusquedaVehiculo, { headers }).map(
      (res: any) => {
        return res;
      }
    );
  }

  registrarVehiculo(vehiculo: Vehiculo) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.post(this.registroVehiculoURL, vehiculo, { headers }).map(
      (res: any) => {
        return res;
      }
    )
  }

  registrarSalidaVehiculo(placa) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    let vehiculoSalida = {
      placa: placa
    }

    return this.http.post(this.saidaVehiculoURL, vehiculoSalida, { headers }).map(
      (res: any) => {
        return res;
      }
    )
  }

}
