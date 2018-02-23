import { browser, by, element } from 'protractor';

export class RegistroPage {
  navigateTo() {
    return browser.get('/registrarVehiculo');
  }

  getInputPlaca() {
    return element(by.css('#placa'));
  }

  getInputCilindraje() {
    return element(by.css('#cilindraje'));
  }

  getInputTipoVehiculo() {
    return element(by.css('#tipoVehiculo'));
  }

  getButtonRegistrar() {
    return element(by.css('#btnRegistrar'));
  }

  getButtonVerListaVehiculos() {
    return element(by.css('#btnVerListaVeh'))
  }
}
