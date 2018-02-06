package co.ceiba.parqueadero.servicio.excepciones;

public class ExcepcionNegocio extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ExcepcionNegocio(String mensaje) {
		super(mensaje);
	}

}
