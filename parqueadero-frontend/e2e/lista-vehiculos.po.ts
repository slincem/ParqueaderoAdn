import { browser, by, element } from 'protractor';

export class ListaVehiculosPage {

  navigateTo() {
    return browser.get('/vehiculos');
  }

  getTableCellsElement() {
    var tabledata = element.all(by.css("#tablaVehiculos"));

    //Get Rows
    var rows = tabledata.all(by.tagName("tr"));

    //Get cells values
    var cells = rows.all(by.tagName("td"));

    return cells;
  }
}
