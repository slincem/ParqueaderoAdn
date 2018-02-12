package co.ceiba.parqueadero.integracion.controlador;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.controlador.VigilanteControlador;
import co.ceiba.parqueadero.databuilder.VehiculoRegistroInDTOTestDataBuilder;
import co.ceiba.parqueadero.modelo.dtos.in.VehiculoRegistroInDTO;
import co.ceiba.parqueadero.modelo.dtos.out.VehiculoEnParqueaderoOutDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class VigilanteControladorTest {

	@Autowired
	private VigilanteControlador vigilanteControlador;

	@Test
	public void getVehiculosEnParqueadero() {

		VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder().build();
		vigilanteControlador.registrarEntradaVehiculoEnParqueadero(vehiculoRegistroInDto);

		ResponseEntity<List<VehiculoEnParqueaderoOutDTO>> listaVehiculosEnParqueadero = vigilanteControlador
				.getVehiculosEnParqueadero();

		Assert.assertEquals(HttpStatus.OK, listaVehiculosEnParqueadero.getStatusCode());
	}

	@Test
	public void getVehiculosEnParqueaderoNoContent() {

		ResponseEntity<List<VehiculoEnParqueaderoOutDTO>> listaVehiculosEnParqueadero = vigilanteControlador
				.getVehiculosEnParqueadero();

		Assert.assertEquals(HttpStatus.NO_CONTENT, listaVehiculosEnParqueadero.getStatusCode());
	}

}
