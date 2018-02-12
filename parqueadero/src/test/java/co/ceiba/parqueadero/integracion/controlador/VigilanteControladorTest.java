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
import co.ceiba.parqueadero.databuilder.VehiculoRegistroSalidaInDTOTestDataBuilder;
import co.ceiba.parqueadero.modelo.dtos.in.VehiculoRegistroInDTO;
import co.ceiba.parqueadero.modelo.dtos.in.VehiculoRegistroSalidaInDTO;
import co.ceiba.parqueadero.modelo.dtos.out.VehiculoEnParqueaderoOutDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class VigilanteControladorTest {

	@Autowired
	private VigilanteControlador vigilanteControlador;

	private String PLACA_NO_EMPIEZA_POR_A = "BCA121";

	@Test
	public void getVehiculosEnParqueadero() {
		// Assert
		VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder().conPlaca(PLACA_NO_EMPIEZA_POR_A).build();
		vigilanteControlador.registrarEntradaVehiculoEnParqueadero(vehiculoRegistroInDto);

		// Act
		ResponseEntity<List<VehiculoEnParqueaderoOutDTO>> listaVehiculosEnParqueadero = vigilanteControlador
				.getVehiculosEnParqueadero();

		// Assert
		Assert.assertEquals(HttpStatus.OK, listaVehiculosEnParqueadero.getStatusCode());
	}

	@Test
	public void getVehiculosEnParqueaderoNoContent() {

		// Act
		ResponseEntity<List<VehiculoEnParqueaderoOutDTO>> listaVehiculosEnParqueadero = vigilanteControlador
				.getVehiculosEnParqueadero();

		// Assert
		Assert.assertEquals(HttpStatus.NO_CONTENT, listaVehiculosEnParqueadero.getStatusCode());
	}

	@Test
	public void getVehiculoEnParqueaderoTest() {
		// Arrange
		VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder().conPlaca(PLACA_NO_EMPIEZA_POR_A).build();
		vigilanteControlador.registrarEntradaVehiculoEnParqueadero(vehiculoRegistroInDto);

		// Act
		ResponseEntity<VehiculoEnParqueaderoOutDTO> vehiculoEnParqueadero = vigilanteControlador
				.getVehiculoEnParqueadero(vehiculoRegistroInDto.getPlaca());

		// Assert
		Assert.assertEquals(HttpStatus.OK, vehiculoEnParqueadero.getStatusCode());
	}

	@Test
	public void getVehiculoEnParqueaderoTestNoContent() {
		// Act
		ResponseEntity<VehiculoEnParqueaderoOutDTO> vehiculoEnParqueadero = vigilanteControlador
				.getVehiculoEnParqueadero(PLACA_NO_EMPIEZA_POR_A);

		// Assert
		Assert.assertEquals(HttpStatus.NO_CONTENT, vehiculoEnParqueadero.getStatusCode());
	}

	@Test
	public void registrarEntradaVehiculoEnParqueaderoTest() {
		// Arrange
		VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder().build();

		// Act
		ResponseEntity<String> registroVehiculoEnParqueadero = vigilanteControlador
				.registrarEntradaVehiculoEnParqueadero(vehiculoRegistroInDto);

		// Assert
		Assert.assertEquals(HttpStatus.ACCEPTED, registroVehiculoEnParqueadero.getStatusCode());
	}

	@Test
	public void registrarEntradaVehiculoEnParqueaderoTestConflict() {
		// Arrange
		VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder().build();
		vigilanteControlador.registrarEntradaVehiculoEnParqueadero(vehiculoRegistroInDto);

		// Act
		ResponseEntity<String> registroVehiculoEnParqueadero = vigilanteControlador
				.registrarEntradaVehiculoEnParqueadero(vehiculoRegistroInDto);

		// Assert
		Assert.assertEquals(HttpStatus.CONFLICT, registroVehiculoEnParqueadero.getStatusCode());
	}
	
	@Test
	public void registrarSalidaVehiculoDeParqueaderoTest() {
		// Arrange
		VehiculoRegistroSalidaInDTO vehiculoRegistroSalidaInDto = new VehiculoRegistroSalidaInDTOTestDataBuilder()
				.conPlaca(PLACA_NO_EMPIEZA_POR_A).build();

		// Act
		ResponseEntity<String> registroSalidaVehiculo = vigilanteControlador
				.registrarSalidaVehiculoDeParqueadero(vehiculoRegistroSalidaInDto);

		// Assert
		Assert.assertEquals(HttpStatus.CONFLICT, registroSalidaVehiculo.getStatusCode());

	}
	
	@Test
	public void registrarSalidaVehiculoDeParqueaderoTestConflict() {
		// Arrange
		VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder().conPlaca(PLACA_NO_EMPIEZA_POR_A).build();
		vigilanteControlador.registrarEntradaVehiculoEnParqueadero(vehiculoRegistroInDto);
		VehiculoRegistroSalidaInDTO vehiculoRegistroSalidaInDto = new VehiculoRegistroSalidaInDTOTestDataBuilder()
				.conPlaca(PLACA_NO_EMPIEZA_POR_A).build();

		// Act
		ResponseEntity<String> registroSalidaVehiculo = vigilanteControlador
				.registrarSalidaVehiculoDeParqueadero(vehiculoRegistroSalidaInDto);

		// Assert
		Assert.assertEquals(HttpStatus.ACCEPTED, registroSalidaVehiculo.getStatusCode());

	}

}
