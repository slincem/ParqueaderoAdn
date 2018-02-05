package co.ceiba.parqueadero.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.parqueadero.modelo.dtos.out.VehiculoEnParqueaderoOutDTO;
import co.ceiba.parqueadero.servicio.VigilanteServicio;

@RestController
@RequestMapping("/parqueadero")
public class VigilanteControlador {

	@Autowired
	private VigilanteServicio vigilanteServicio;

	@RequestMapping(value = "/listarVehiculos", method = RequestMethod.GET)
	public ResponseEntity<List<VehiculoEnParqueaderoOutDTO>> getVehiculosEnParqueadero() {
		List<VehiculoEnParqueaderoOutDTO> listaVehiculosEnParqueadero = vigilanteServicio
				.consultarVehiculosEnParqueadero();
		
		if(listaVehiculosEnParqueadero.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(listaVehiculosEnParqueadero, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarVehiculo/{placa}", method = RequestMethod.GET)
	public ResponseEntity<VehiculoEnParqueaderoOutDTO> getVehiculoEnParqueadero(@PathVariable("placa") String placa) {
		VehiculoEnParqueaderoOutDTO vehiculoEnParqueaderoDto = vigilanteServicio.consultarVehiculoEnParqueadero(placa);
		
		if(vehiculoEnParqueaderoDto == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
			
		return new ResponseEntity<>(vehiculoEnParqueaderoDto, HttpStatus.OK);
	}

}
