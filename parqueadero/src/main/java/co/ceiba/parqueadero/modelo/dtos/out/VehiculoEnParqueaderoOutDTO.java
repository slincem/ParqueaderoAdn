package co.ceiba.parqueadero.modelo.dtos.out;

import java.text.SimpleDateFormat;
import java.util.Date;

import co.ceiba.parqueadero.utilitario.FechaUtilitario;

public class VehiculoEnParqueaderoOutDTO {
	
	private String fechaIngreso;
	private String fechaSalida;
	private String placa;
	private int cilindraje;
	
	public VehiculoEnParqueaderoOutDTO(Date fechaIngreso, Date fechaSalida, String placa, int cilindraje) {
		super();
		
		SimpleDateFormat formateador = new SimpleDateFormat(FechaUtilitario.FORMATO_FECHA);
		this.fechaIngreso = formateador.format(fechaIngreso);
		this.fechaSalida = formateador.format(fechaSalida);
		this.placa = placa;
		this.cilindraje = cilindraje;
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
	
	 
}
