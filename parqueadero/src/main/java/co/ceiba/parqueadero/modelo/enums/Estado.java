package co.ceiba.parqueadero.modelo.enums;

public enum Estado {
	
	ACTIVO (1),
	INACTIVO (2);
	
	private int estadoVehiculo;
	
	private Estado(int estadoVehiculo) {
		this.estadoVehiculo = estadoVehiculo;
	}

	public int getEstadoVehiculo() {
		return estadoVehiculo;
	}	
	
}
