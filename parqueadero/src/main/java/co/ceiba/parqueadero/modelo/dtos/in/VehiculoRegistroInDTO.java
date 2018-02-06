package co.ceiba.parqueadero.modelo.dtos.in;

import java.io.Serializable;

public class VehiculoRegistroInDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private String placa;
	private int cilindraje;
	private String tipoVehiculo;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

}
