package co.ceiba.parqueadero.databuilder;

import co.ceiba.parqueadero.modelo.dtos.in.VehiculoRegistroSalidaInDTO;

public class VehiculoRegistroSalidaInDTOTestDataBuilder {
	
	private String placa;
	
	public VehiculoRegistroSalidaInDTOTestDataBuilder() {
		this.placa = "ABC001";
	}
	
	public VehiculoRegistroSalidaInDTOTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public VehiculoRegistroSalidaInDTO build() {
		VehiculoRegistroSalidaInDTO vehiculoRegistroSalidaInDto = new VehiculoRegistroSalidaInDTO();
		vehiculoRegistroSalidaInDto.setPlaca(this.placa);
		return vehiculoRegistroSalidaInDto;
	}
}
