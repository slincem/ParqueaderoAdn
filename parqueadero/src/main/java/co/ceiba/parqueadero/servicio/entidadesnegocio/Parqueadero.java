package co.ceiba.parqueadero.servicio.entidadesnegocio;


public class Parqueadero {

	public static final char LETRA_RESTRINGIDA = 'A';

	public static final double VALOR_HORA_CARRO = 1000;
	public static final double VALOR_HORA_MOTO = 500;
	public static final double VALOR_DIA_CARRO = 8000;
	public static final double VALOR_DIA_MOTO = 4000;

	public static final int CILINDRAJE_PARA_RECARGO = 500;
	public static final double VALOR_RECARGO_POR_CILINDRAJE = 2000;

	public static final double HORAS_INICIO_PARA_COBRAR_POR_DIA = 9;
	public static final double HORAS_FINAL_PARA_COBRAR_POR_DIA = 24;
	
	public static final int CANTIDAD_MAXIMA_DE_CARROS_ADMITIDOS = 20;
	public static final int CANTIDAD_MAXIMA_DE_MOTOS_ADMITIDAS = 10;

	private Parqueadero() {

	}

}
