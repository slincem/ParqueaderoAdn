package co.ceiba.parqueadero.integracion.servicio.implementaciones;

import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.Date;

import javax.validation.constraints.AssertTrue;

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
import co.ceiba.parqueadero.modelo.dtos.in.VehiculoRegistroInDTO;
import co.ceiba.parqueadero.modelo.entidades.Vehiculo;
import co.ceiba.parqueadero.modelo.enums.TipoVehiculo;
import co.ceiba.parqueadero.repositorio.HistoricoParqueaderoRepositorio;
import co.ceiba.parqueadero.repositorio.VehiculoRepositorio;
import co.ceiba.parqueadero.servicio.implementaciones.VigilanteServicioImpl;
import co.ceiba.parqueadero.utilitario.FechaUtilitario;

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
	
	//Varios parametros
	@Test
	public void verificarInicioDePlacaPorLetraRegistrindaTest() {
		// // Arrange
		// VigilanteServicioImpl vigilanteServicioImpl = new VigilanteServicioImpl();

		// Act
		boolean empiezaPorA = vigilanteServicioImpl.empiezaPorLetraRegistrinda("ABC001");
		// Assert
		Assert.assertTrue(empiezaPorA);
	}
	
	//Varios parametros
	@Test
	public void verificarDiasDeRestriccionDeLetraTest() {
		// Arrange
		// VigilanteServicioImpl viliganteServicioImpl = new VigilanteServicioImpl();

		Date fechaDomingo = null;
		try {
			fechaDomingo = FechaUtilitario.formatearStringAFecha("04/02/2018 12:01:01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Act
		boolean esDomingoOLunes = vigilanteServicioImpl.verificarDiasDeRestriccionDeLetra(fechaDomingo);
		// Assert
		Assert.assertTrue(esDomingoOLunes);
	}
	
	//Varios parametros
	@Test
	public void esPosibleEntradaDelVehiculoSegunPlacaTest() {
		// Arrange
		// VigilanteServicioImpl viliganteServicioImpl = new VigilanteServicioImpl();

		String placa = "ABC001";

		Date fechaDomingo = null;
		try {
			fechaDomingo = FechaUtilitario.formatearStringAFecha("04/02/2018 12:01:01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
	
	//Varios parametros
	@Test
	public void verificarCupoParaVehiculoTest() {
		Mockito.when(historicoParqueaderoRepositorio.contarVehiculosEnParqueadero(Mockito.any())).thenReturn(20);
		
		boolean hayCupoParaVehiculo = vigilanteServicioImpl.verificarCupoParaVehiculo(TipoVehiculo.CARRO);
		
		Assert.assertFalse(hayCupoParaVehiculo);
	}

}
