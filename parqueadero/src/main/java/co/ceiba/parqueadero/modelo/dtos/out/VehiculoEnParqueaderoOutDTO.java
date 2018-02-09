package co.ceiba.parqueadero.modelo.dtos.out;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.ceiba.parqueadero.modelo.enums.TipoVehiculo;
import co.ceiba.parqueadero.utilitario.FechaUtilitario;

public class VehiculoEnParqueaderoOutDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fechaIngreso;
	private String fechaSalida;
	private String placa;
	private String tipoVehiculo;
	private int cilindraje;
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

	public double getValorAPagar() {
		return valorAPagar;
	}

	public void setValorAPagar(double valorAPagar) {
		this.valorAPagar = valorAPagar;
	}
}
