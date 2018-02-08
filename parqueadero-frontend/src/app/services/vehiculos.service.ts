import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Vehiculo } from '../interfaces/vehiculo.interface';
import 'rxjs/Rx';

@Injectable()
export class VehiculosService {

  vehiculosURL: string = "http://localhost:8080/parqueadero/listarVehiculos";
  registroVehiculo: string = "http://localhost:8080/parqueadero/registrarEntradaVehiculo";

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

  registrarVehiculo(vehiculo: Vehiculo) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.post(this.registroVehiculo, vehiculo, { headers }).map(
      (res: any) => {
        return res;
      }
    )
  }

}
