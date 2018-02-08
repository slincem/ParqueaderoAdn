package co.ceiba.parqueadero.servicio;

import java.util.List;

import co.ceiba.parqueadero.modelo.dtos.in.VehiculoRegistroInDTO;
import co.ceiba.parqueadero.modelo.dtos.in.VehiculoRegistroSalidaInDTO;
import co.ceiba.parqueadero.modelo.dtos.out.VehiculoEnParqueaderoOutDTO;

public interface VigilanteServicio {
	
	public List<VehiculoEnParqueaderoOutDTO> consultarVehiculosEnParqueadero();
	
	public VehiculoEnParqueaderoOutDTO consultarVehiculoEnParqueadero(String placa);
	
	public boolean registrarVehiculoEnParqueadero(VehiculoRegistroInDTO vehiculoRegistroInDto);
	
	public Double registrarSalidaVehiculoDeParqueadero(VehiculoRegistroSalidaInDTO vehiculoRegistroSalidaInDto);

}
