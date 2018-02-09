package co.ceiba.parqueadero.databuilder;

import co.ceiba.parqueadero.modelo.dtos.in.VehiculoRegistroInDTO;

public class VehiculoRegistroInDTOTestDataBuilder {
	
	private String placa;
	private int cilindraje;
	private String tipoVehiculo;
	
	public VehiculoRegistroInDTOTestDataBuilder() {
		this.placa = "ABC001";
		this.cilindraje = 2000;
		this.tipoVehiculo = "Carro";
	}
	
	public VehiculoRegistroInDTOTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public VehiculoRegistroInDTOTestDataBuilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}
	
	public VehiculoRegistroInDTOTestDataBuilder conTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}
	
	public VehiculoRegistroInDTO build() {
		VehiculoRegistroInDTO vehiculoRegistroInDTO = new VehiculoRegistroInDTO();
		vehiculoRegistroInDTO.setPlaca(this.placa);
		vehiculoRegistroInDTO.setCilindraje(this.cilindraje);
		vehiculoRegistroInDTO.setTipoVehiculo(this.tipoVehiculo);
		return vehiculoRegistroInDTO;
	}
}
