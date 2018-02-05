package co.ceiba.parqueadero.servicio;

import java.util.List;

import co.ceiba.parqueadero.modelo.dtos.out.VehiculoEnParqueaderoOutDTO;

public interface VigilanteServicio {
	
	public List<VehiculoEnParqueaderoOutDTO> consultarVehiculosEnParqueadero();
	
	public VehiculoEnParqueaderoOutDTO consultarVehiculoEnParqueadero(String placa);

}
