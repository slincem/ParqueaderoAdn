package co.ceiba.parqueadero.databuilder;

import co.ceiba.parqueadero.modelo.entidades.Vehiculo;
import co.ceiba.parqueadero.modelo.enums.TipoVehiculo;

public class VehiculoTestDataBuilder {

	private Long id;
	private String placa;
	private int cilindraje;
	private TipoVehiculo tipoVehiculo;

	public VehiculoTestDataBuilder() {
		super();
		this.id = 2L;
		this.placa = "ABC001";
		this.cilindraje = 2000;
		this.tipoVehiculo = TipoVehiculo.CARRO;
	}
	
	public VehiculoTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
	
	public VehiculoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public VehiculoTestDataBuilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}
	
	public VehiculoTestDataBuilder conTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}
	
	public Vehiculo build() {
		Vehiculo vehiculo = new Vehiculo(this.placa, this.cilindraje, this.tipoVehiculo);
		vehiculo.setId(this.id);
		return vehiculo;
	}

}
