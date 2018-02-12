package co.ceiba.parqueadero.modelo.dtos.out;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.ceiba.parqueadero.modelo.dtos.VehiculoDTO;
import co.ceiba.parqueadero.modelo.enums.TipoVehiculo;
import co.ceiba.parqueadero.utilitario.FechaUtilitario;

public class VehiculoEnParqueaderoOutDTO extends VehiculoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fechaIngreso;
	private String fechaSalida;
	private double valorAPagar;

	public VehiculoEnParqueaderoOutDTO(Date fechaIngreso, Date fechaSalida, String placa, int cilindraje,
			TipoVehiculo tipoVehiculo) {
		super();

		SimpleDateFormat formateador = new SimpleDateFormat(FechaUtilitario.FORMATO_FECHA);
		this.fechaIngreso = fechaIngreso != null ? formateador.format(fechaIngreso) : null;
		this.fechaSalida = fechaSalida != null ? formateador.format(fechaSalida) : null;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.tipoVehiculo = tipoVehiculo.getValue();
	}
	
	public VehiculoEnParqueaderoOutDTO() {
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public double getValorAPagar() {
		return valorAPagar;
	}

	public void setValorAPagar(double valorAPagar) {
		this.valorAPagar = valorAPagar;
	}
}
