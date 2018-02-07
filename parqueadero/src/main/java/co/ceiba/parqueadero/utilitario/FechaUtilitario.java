package co.ceiba.parqueadero.utilitario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaUtilitario {

	public static final String FORMATO_FECHA = "dd/MM/yyyy HH:mm:ss";

	public static long calcularDiferenciaEnHorasEntreFechas(Date fechaInicial, Date fechaFinal) {
		long diferencia = fechaFinal.getTime() - fechaInicial.getTime();
		return diferencia / (60 * 60 * 1000) % 24;

	}
	
	public static long calcularDiferenciaEnDiasEntreFechas(Date fechaInicial, Date fechaFinal) {
		long diferencia = fechaFinal.getTime() - fechaInicial.getTime();
		return diferencia / (24 * 60 * 60 * 1000);
	}
	
	public static Date formatearStringAFecha(String fecha) throws ParseException {
		SimpleDateFormat formateador = new SimpleDateFormat(FechaUtilitario.FORMATO_FECHA);
		return formateador.parse(fecha);
	}

	private FechaUtilitario() {

	}
}
