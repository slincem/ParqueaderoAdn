import { RegistroPage } from './app.po';
import { ListaVehiculosPage } from './lista-vehiculos.po';

describe('parqueadero-frontend App', () => {
  let registroPage: RegistroPage;
  let vehiculosPage: ListaVehiculosPage;

  beforeEach(() => {
    registroPage = new RegistroPage();
    vehiculosPage = new ListaVehiculosPage();
  });

  it('Registrar vehiculo en el parqueadero', () => {
    registroPage.navigateTo();
    let placa = "CBA001";
    registroPage.getInputPlaca().sendKeys(placa);
    registroPage.getInputCilindraje().sendKeys(860);
    registroPage.getInputTipoVehiculo().sendKeys("Moto");

    registroPage.getButtonRegistrar().click();

    registroPage.getButtonVerListaVehiculos().click();
    expect(vehiculosPage.getTableCellsElement().get(1).getText()).toEqual(placa);
  });
});
