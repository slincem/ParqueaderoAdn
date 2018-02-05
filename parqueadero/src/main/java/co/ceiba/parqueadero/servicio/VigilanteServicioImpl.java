package co.ceiba.parqueadero.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parqueadero.modelo.dtos.out.VehiculoEnParqueaderoOutDTO;
import co.ceiba.parqueadero.repositorio.HistoricoParqueaderoRepositorio;

@Service
public class VigilanteServicioImpl implements VigilanteServicio {
	
	@Autowired
	private HistoricoParqueaderoRepositorio historicoParqueaderoRepositorio;

	@Override
	public List<VehiculoEnParqueaderoOutDTO> consultarVehiculosEnParqueadero() {
		return historicoParqueaderoRepositorio.consultarVehiculosEnParqueadero();
	}

	@Override
	public VehiculoEnParqueaderoOutDTO consultarVehiculoEnParqueadero(String placa) {
		
		return historicoParqueaderoRepositorio.consultarVehiculoEnParqueadero(placa);
	}

}
