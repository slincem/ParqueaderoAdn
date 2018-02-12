package co.ceiba.parqueadero.unitaria.servicio.implementaciones;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.databuilder.VehiculoRegistroInDTOTestDataBuilder;
import co.ceiba.parqueadero.databuilder.VehiculoTestDataBuilder;
import co.ceiba.parqueadero.modelo.dtos.in.VehiculoRegistroInDTO;
import co.ceiba.parqueadero.modelo.entidades.HistoricoParqueadero;
import co.ceiba.parqueadero.modelo.entidades.Vehiculo;
import co.ceiba.parqueadero.modelo.enums.TipoVehiculo;
import co.ceiba.parqueadero.repositorio.HistoricoParqueaderoRepositorio;
import co.ceiba.parqueadero.repositorio.VehiculoRepositorio;
import co.ceiba.parqueadero.servicio.excepciones.ExcepcionNegocio;
import co.ceiba.parqueadero.servicio.implementaciones.VigilanteServicioImpl;
import co.ceiba.parqueadero.utilitario.FechaUtilitario;
import co.ceiba.parqueadero.utilitario.constantes.MensajesError;

@RunWith(SpringRunner.class)
public class VigilanteServicioImplTest {

	@InjectMocks
	private VigilanteServicioImpl vigilanteServicioImpl;

	@Mock
	private HistoricoParqueaderoRepositorio historicoParqueaderoRepositorio;

	@Mock
	private VehiculoRepositorio vehiculoRepositorio;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void consultarVehiculosEnParqueaderoTest() {

		// List<Vehiculo>
		// Mockito.when(historicoParqueaderoRepositorio.consultarVehiculosEnParqueadero()).thenReturn(value)
	}

	// Varios parametros
	@Test
	public void verificarInicioDePlacaPorLetraRegistrindaTest() {
		// // Arrange
		// VigilanteServicioImpl vigilanteServicioImpl = new VigilanteServicioImpl();

		// Act
		boolean empiezaPorA = vigilanteServicioImpl.empiezaPorLetraRegistrinda("ABC001");
		// Assert
		Assert.assertTrue(empiezaPorA);
	}

	// Varios parametros
	@Test
	public void verificarDiasDeRestriccionDeLetraTest() {
		// Arrange
		// VigilanteServicioImpl viliganteServicioImpl = new VigilanteServicioImpl();

		Date fechaDomingo = null;
		try {
			fechaDomingo = FechaUtilitario.formatearStringAFecha("04/02/2018 12:01:01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// Act
		boolean esDomingoOLunes = vigilanteServicioImpl.verificarDiasDeRestriccionDeLetra(fechaDomingo);
		// Assert
		Assert.assertTrue(esDomingoOLunes);
	}

	// Varios parametros
	@Test
	public void esPosibleEntradaDelVehiculoSegunPlacaTest() {
		// Arrange
		// VigilanteServicioImpl viliganteServicioImpl = new VigilanteServicioImpl();

		String placa = "ABC001";

		Date fechaDomingo = null;
		try {
			fechaDomingo = FechaUtilitario.formatearStringAFecha("04/02/2018 12:01:01");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Act
		boolean vehiculoPuedeIngresar = vigilanteServicioImpl.esPosibleEntradaDelVehiculoSegunPlaca(placa,
				fechaDomingo);

		// Assert
		Assert.assertTrue(vehiculoPuedeIngresar);
	}

	@Test
	public void comprobarExistenciaDeVehiculoTest() {
		// VigilanteServicioImpl viliganteServicioImpl = new VigilanteServicioImpl();

		// Arrange
		Mockito.when(vehiculoRepositorio.findByPlaca(Mockito.anyString())).thenReturn(new Vehiculo());

		// Act
		boolean vehiculoFueEncontrado = vigilanteServicioImpl.comprobarExistenciaDeVehiculo("ABC001");

		// Assert
		Assert.assertTrue(vehiculoFueEncontrado);

	}

	@Test
	public void convertirVehiculoRegistroInDtoAVehiculoTest() {
		// Arrange
		VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder().build();

		// Act
		Vehiculo vehiculo = vigilanteServicioImpl.convertirVehiculoRegistroInDtoAVehiculo(vehiculoRegistroInDto);
		boolean vehiculoYDtoSonIguales = true;
		if (vehiculo.getCilindraje() != vehiculoRegistroInDto.getCilindraje()
				|| vehiculo.getPlaca() != vehiculoRegistroInDto.getPlaca()
				|| !vehiculo.getTipoVehiculo().getValue().equals(vehiculoRegistroInDto.getTipoVehiculo()))
			vehiculoYDtoSonIguales = false;

		// Assert
		Assert.assertTrue(vehiculoYDtoSonIguales);
	}

	// Varios parametros
	@Test
	public void verificarCupoParaVehiculoTest() {
		// Arrange
		Mockito.when(historicoParqueaderoRepositorio.contarVehiculosEnParqueadero(Mockito.any())).thenReturn(20);

		// Act
		boolean hayCupoParaVehiculo = vigilanteServicioImpl.verificarCupoParaVehiculo(TipoVehiculo.CARRO);

		// Assert
		Assert.assertFalse(hayCupoParaVehiculo);
	}
	
	@Test
	public void verificarCupoParaVehiculoTestError() {
		// Arrange
		Mockito.when(historicoParqueaderoRepositorio.contarVehiculosEnParqueadero(Mockito.any())).thenReturn(20);

		// Act
		boolean hayCupoParaVehiculo = vigilanteServicioImpl.verificarCupoParaVehiculo(TipoVehiculo.CARRO);

		// Assert
		Assert.assertFalse(hayCupoParaVehiculo);
	}

	// Varios parametros
	@Test
	public void calcularValorAPagarEnParqueaderoTest() {
		// Arrange
		Date fechaIngreso = null;
		Date fechaSalida = null;
		try {
			fechaIngreso = FechaUtilitario.formatearStringAFecha("09/02/2018 08:00:00");
			fechaSalida = FechaUtilitario.formatearStringAFecha("09/02/2018 17:05:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		double precision = 001;

		// Act
		double valorACancelar = vigilanteServicioImpl.calcularValorAPagarEnParqueadero(fechaIngreso, fechaSalida,
				TipoVehiculo.MOTO, 650);

		// Assert
		assertEquals(6000, valorACancelar, precision);
	}

	// Varios parametros
	@Test
	public void validarDatosDeRegistroTestErrorTipoVehiculo() {
		// Arrange
		VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder()
				.conTipoVehiculo("Avión").build();

		try {
			// Act
			vigilanteServicioImpl.validarDatosDeRegistro(vehiculoRegistroInDto);
			fail();
		} catch (ExcepcionNegocio excepcion) {
			// Assert
			Assert.assertEquals(MensajesError.TIPO_VEHICULO_NO_VALIDO, excepcion.getMessage());
		}

	}
	
	// Varios parametros
	@Test
	public void validarDatosDeRegistroTestErrorPlaca() {
		// Arrange
		VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder().conPlaca("-BCD00-")
				.build();
		try {
			// Act
			vigilanteServicioImpl.validarDatosDeRegistro(vehiculoRegistroInDto);
			fail();
		} catch (ExcepcionNegocio excepcion) {
			// Assert
			Assert.assertEquals(MensajesError.PLACA_NO_VALIDA, excepcion.getMessage());
		}
	}
	
	@Test
	public void validarDatosDeRegistroTestErrorCilindraje() {
		// Arrange
		VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder().conCilindraje(0)
				.build();
		try {
			// Act
			vigilanteServicioImpl.validarDatosDeRegistro(vehiculoRegistroInDto);
			fail();
		} catch (ExcepcionNegocio excepcion) {
			// Assert
			Assert.assertEquals(MensajesError.CILINDRAJE_NO_VALIDO, excepcion.getMessage());
		}
	}
	
	@Test
	public void construirHistoricoParqueadero() {
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		
		//Act
		HistoricoParqueadero historicoParqueadero = vigilanteServicioImpl.construirHistoricoParqueadero(vehiculo);
		
		//Assert
		Assert.assertNotNull(historicoParqueadero);
	}
	
	@Test
	public void registrarVehiculoPorPrimeraVezTest() {
		
		VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder().build();
		Mockito.when(vehiculoRepositorio.save(Mockito.any(Vehiculo.class))).thenReturn(new Vehiculo());
		
		Vehiculo vehiculo = vigilanteServicioImpl.registrarVehiculoPorPrimeraVez(vehiculoRegistroInDto);
		Assert.assertNotNull(vehiculo);
	}
	
	@Test
	public void actualizarDatosVehiculoTest() {
		
		VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder().build();
		Mockito.doNothing().when(vehiculoRepositorio);
		
		Vehiculo vehiculo = vigilanteServicioImpl.actualizarDatosVehiculo(vehiculoRegistroInDto);
		Assert.assertNotNull(vehiculo);
	}

}
