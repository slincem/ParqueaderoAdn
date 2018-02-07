import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import 'rxjs/Rx';

@Injectable()
export class VehiculosService {

  vehiculosURL: string = "http://localhost:8080/parqueadero/listarVehiculos";

  constructor(public http:HttpClient) { }

  getVehiculos() {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.get(this.vehiculosURL, { headers }).map(
      (res:any) => {
        return res;
      }
    );
  }

}
