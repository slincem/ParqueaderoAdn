package co.ceiba.parqueadero.utilitario.constantes;

public class MensajesError {

	public static final String VEHICULO_NO_ESTA_EN_PARQUEADERO = "Este vehiculo no se encuentra dentro de las instalaciones";

	public static final String VEHICULO_YA_ESTA_EN_PARQUEADERO = "El vehiculo ya se encuentra dentro de las instalaciones";

	public static final String HISTORICO_DE_REGISTRO_DEL_VEHICULO_INEXISTENTE = "Este vehiculo no posee un registro activo en el parqueadero";

	public static final String PLACA_DE_VEHICULO_NO_PERMITIDA_ESTE_DIA = "La placa del vehiculo corresponde con aquellas restringidas para el día de hoy";

	public static final String NO_HAY_DISPONIBILIDAD_DE_CUPO = "No hay cupo disponible para este vehiculo";

	public static final String TIPO_VEHICULO_NO_VALIDO = "Este tipo de vehículo de inválido";

	public static final String PLACA_NO_VALIDA = "Esta placa tiene un formato no válido, sólo se permite un dato alfanúmerico";
	
	public static final String CILINDRAJE_NO_VALIDO = "El cilindraje ingresado no es válido";

	private MensajesError() {

	}
}
