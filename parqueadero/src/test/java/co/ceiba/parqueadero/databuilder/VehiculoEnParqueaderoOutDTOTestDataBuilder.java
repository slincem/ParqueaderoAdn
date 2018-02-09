package co.ceiba.parqueadero.databuilder;

import co.ceiba.parqueadero.modelo.dtos.out.VehiculoEnParqueaderoOutDTO;

public class VehiculoEnParqueaderoOutDTOTestDataBuilder {
	private String fechaIngreso;
	private String fechaSalida;
	private String placa;
	private String tipoVehiculo;
	private int cilindraje;
	private double valorAPagar;

	public VehiculoEnParqueaderoOutDTOTestDataBuilder() {
		this.fechaIngreso = "26/01/2018 10:00:00";
		this.fechaSalida = "26/01/2018 20:00:00";
		this.placa = "ABC001";
		this.tipoVehiculo = "Carro";
	}

	public VehiculoEnParqueaderoOutDTOTestDataBuilder conFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public VehiculoEnParqueaderoOutDTOTestDataBuilder conFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}

	public VehiculoEnParqueaderoOutDTOTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public VehiculoEnParqueaderoOutDTOTestDataBuilder conTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}

	public VehiculoEnParqueaderoOutDTOTestDataBuilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public VehiculoEnParqueaderoOutDTOTestDataBuilder conValorAPagar(double valorAPagar) {
		this.valorAPagar = valorAPagar;
		return this;
	}

	public VehiculoEnParqueaderoOutDTO build() {
		VehiculoEnParqueaderoOutDTO vehiculoEnParqueaderoOutDto = new VehiculoEnParqueaderoOutDTO();
		vehiculoEnParqueaderoOutDto.setPlaca(this.placa);
		vehiculoEnParqueaderoOutDto.setFechaIngreso(this.fechaIngreso);
		vehiculoEnParqueaderoOutDto.setFechaSalida(this.fechaSalida);
		vehiculoEnParqueaderoOutDto.setTipoVehiculo(this.tipoVehiculo);
		vehiculoEnParqueaderoOutDto.setCilindraje(this.cilindraje);
		vehiculoEnParqueaderoOutDto.setValorAPagar(this.valorAPagar);
		return vehiculoEnParqueaderoOutDto;
	}
}
