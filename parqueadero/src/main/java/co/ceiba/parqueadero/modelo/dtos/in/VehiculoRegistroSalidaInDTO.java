package co.ceiba.parqueadero.modelo.dtos.in;

import java.io.Serializable;

public class VehiculoRegistroSalidaInDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String placa;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

}
