package co.ceiba.parqueadero.modelo.enums;

public enum Estado {
	
	ACTIVO ("Activo"),
	INACTIVO ("Inactivo");
	
	private String estadoVehiculo;
	
	private Estado(String estadoVehiculo) {
		this.estadoVehiculo = estadoVehiculo;
	}

	public String getEstadoVehiculo() {
		return estadoVehiculo;
	}	
	
}
