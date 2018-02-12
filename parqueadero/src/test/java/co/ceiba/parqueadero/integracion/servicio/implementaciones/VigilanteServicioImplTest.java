package co.ceiba.parqueadero.integracion.servicio.implementaciones;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.databuilder.VehiculoRegistroInDTOTestDataBuilder;
import co.ceiba.parqueadero.databuilder.VehiculoRegistroSalidaInDTOTestDataBuilder;
import co.ceiba.parqueadero.modelo.dtos.in.VehiculoRegistroInDTO;
import co.ceiba.parqueadero.modelo.dtos.in.VehiculoRegistroSalidaInDTO;
import co.ceiba.parqueadero.modelo.dtos.out.VehiculoEnParqueaderoOutDTO;
import co.ceiba.parqueadero.modelo.entidades.HistoricoParqueadero;
import co.ceiba.parqueadero.repositorio.HistoricoParqueaderoRepositorio;
import co.ceiba.parqueadero.servicio.entidadesnegocio.Parqueadero;
import co.ceiba.parqueadero.servicio.excepciones.ExcepcionNegocio;
import co.ceiba.parqueadero.servicio.implementaciones.VigilanteServicioImpl;
import co.ceiba.parqueadero.utilitario.constantes.ConstantesNumeros;
import co.ceiba.parqueadero.utilitario.constantes.MensajesError;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class VigilanteServicioImplTest {

	@Autowired
	VigilanteServicioImpl vigilanteServicioImpl;

	@Autowired
	HistoricoParqueaderoRepositorio historicoParqueaderoRepositorio;

	private static final String PLACA_NO_EMPIEZA_POR_A = "BCA001";

	@Test
	public void consultarVehiculosEnParqueaderoTest() {
		// Arrange
		VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder()
				.conPlaca(PLACA_NO_EMPIEZA_POR_A).build();
		vigilanteServicioImpl.registrarVehiculoEnParqueadero(vehiculoRegistroInDto);

		// Act
		List<VehiculoEnParqueaderoOutDTO> vehiculosEnParqueaderoList = vigilanteServicioImpl
				.consultarVehiculosEnParqueadero();

		if (vehiculosEnParqueaderoList == null || vehiculosEnParqueaderoList.isEmpty()) {
			fail();
		}

		// Assert
		Assert.assertEquals(vehiculoRegistroInDto.getPlaca(), vehiculosEnParqueaderoList.get(0).getPlaca());
	}

	@Test
	public void consultarVehiculoEnParqueaderoTest() {
		// Arrange
		VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder()
				.conPlaca(PLACA_NO_EMPIEZA_POR_A).build();
		vigilanteServicioImpl.registrarVehiculoEnParqueadero(vehiculoRegistroInDto);

		// Act
		VehiculoEnParqueaderoOutDTO vehiculoEnParqueaderoOutDto = vigilanteServicioImpl
				.consultarVehiculoEnParqueadero(PLACA_NO_EMPIEZA_POR_A);

		if (vehiculoEnParqueaderoOutDto == null) {
			fail();
		}

		// Assert
		Assert.assertEquals(PLACA_NO_EMPIEZA_POR_A, vehiculoEnParqueaderoOutDto.getPlaca());
	}

	@Test
	public void registrarVehiculoEnParqueaderoTest() {
		// Arrange
		VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder()
				.conPlaca(PLACA_NO_EMPIEZA_POR_A).build();
		vigilanteServicioImpl.registrarVehiculoEnParqueadero(vehiculoRegistroInDto);

		// Act
		HistoricoParqueadero historicoParqueadero = historicoParqueaderoRepositorio
				.consultarHistoricoParqueaderoVehiculoEnParqueadero(vehiculoRegistroInDto.getPlaca());

		// Assert
		assertNotNull(historicoParqueadero);
	}

	@Test
	public void registrarVehiculoEnParqueaderoTestErrorVehiculoYaSeEncuentra() {
		// Arrange
		VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder().build();
		vigilanteServicioImpl.registrarVehiculoEnParqueadero(vehiculoRegistroInDto);
		try {
			// Act
			vigilanteServicioImpl.registrarVehiculoEnParqueadero(vehiculoRegistroInDto);
			fail();
		} catch (ExcepcionNegocio e) {
			// Assert
			Assert.assertEquals(MensajesError.VEHICULO_YA_ESTA_EN_PARQUEADERO, e.getMessage());
		}
	}

	@Test
	public void registrarVehiculoEnParqueaderoTestErrorCupoCarro() {
		// Arrange
		for (int i = ConstantesNumeros.NUMERO_CERO; i < Parqueadero.CANTIDAD_MAXIMA_DE_CARROS_ADMITIDOS; i++) {
			VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder()
					.conPlaca(PLACA_NO_EMPIEZA_POR_A + i).build();
			vigilanteServicioImpl.registrarVehiculoEnParqueadero(vehiculoRegistroInDto);
		}

		try {
			// Act
			VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder()
					.conPlaca(PLACA_NO_EMPIEZA_POR_A + 50).build();
			vigilanteServicioImpl.registrarVehiculoEnParqueadero(vehiculoRegistroInDto);
		} catch (Exception e) {
			// Assert
			Assert.assertEquals(MensajesError.NO_HAY_DISPONIBILIDAD_DE_CUPO, e.getMessage());
		}
	}

	@Test
	public void registrarVehiculoEnParqueaderoTestErrorCupoMoto() {
		// Arrange
		for (int i = ConstantesNumeros.NUMERO_CERO; i < Parqueadero.CANTIDAD_MAXIMA_DE_MOTOS_ADMITIDAS; i++) {
			VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder()
					.conPlaca(PLACA_NO_EMPIEZA_POR_A + i).conTipoVehiculo("Moto").build();
			vigilanteServicioImpl.registrarVehiculoEnParqueadero(vehiculoRegistroInDto);
		}

		try {
			// Act
			VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder()
					.conPlaca(PLACA_NO_EMPIEZA_POR_A + 50).conTipoVehiculo("Moto").build();
			vigilanteServicioImpl.registrarVehiculoEnParqueadero(vehiculoRegistroInDto);
		} catch (Exception e) {
			// Assert
			Assert.assertEquals(MensajesError.NO_HAY_DISPONIBILIDAD_DE_CUPO, e.getMessage());
		}
	}
	
	@Test
	public void registrarSalidaVehiculoDeParqueaderoTest() {
		// Arrange
		VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder()
				.conPlaca(PLACA_NO_EMPIEZA_POR_A).build();
		vigilanteServicioImpl.registrarVehiculoEnParqueadero(vehiculoRegistroInDto);

		VehiculoRegistroSalidaInDTO vehiculoRegistroSalidaInDTO = new VehiculoRegistroSalidaInDTOTestDataBuilder()
				.conPlaca(PLACA_NO_EMPIEZA_POR_A).build();

		double precision = 001;

		// Act
		double valorAPagar = vigilanteServicioImpl.registrarSalidaVehiculoDeParqueadero(vehiculoRegistroSalidaInDTO);

		// Assert
		Assert.assertEquals(Parqueadero.VALOR_HORA_CARRO, valorAPagar, precision);

	}

	@Test
	public void registrarSalidaVehiculoDeParqueaderoTestErrorVehiculoNoEstaEnParqueadero() {
		// Arrange
		VehiculoRegistroSalidaInDTO vehiculoRegistroSalidaInDTO = new VehiculoRegistroSalidaInDTOTestDataBuilder()
				.conPlaca(PLACA_NO_EMPIEZA_POR_A).build();

		try {
			// Act
			vigilanteServicioImpl.registrarSalidaVehiculoDeParqueadero(vehiculoRegistroSalidaInDTO);
			fail();
		} catch (ExcepcionNegocio e) {
			// Assert
			Assert.assertEquals(MensajesError.VEHICULO_NO_ESTA_EN_PARQUEADERO, e.getMessage());
		}
	}
}
