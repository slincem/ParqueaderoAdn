package co.ceiba.parqueadero.unitaria.servicio.implementaciones;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.databuilder.VehiculoRegistroInDTOTestDataBuilder;
import co.ceiba.parqueadero.modelo.dtos.in.VehiculoRegistroInDTO;
import co.ceiba.parqueadero.modelo.dtos.out.VehiculoEnParqueaderoOutDTO;
import co.ceiba.parqueadero.servicio.implementaciones.VigilanteServicioImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VigilanteServicioImplTest {
	
	@Autowired
	VigilanteServicioImpl vigilanteServicioImpl;
	
	@Test
	public void consultarVehiculosEnParqueaderoTest() {
		VehiculoRegistroInDTO vehiculoRegistroInDto = new VehiculoRegistroInDTOTestDataBuilder().conPlaca("BCA001").build();
		
		vigilanteServicioImpl.registrarVehiculoEnParqueadero(vehiculoRegistroInDto);
		
		List<VehiculoEnParqueaderoOutDTO> vehiculosEnParqueaderoList = vigilanteServicioImpl.consultarVehiculosEnParqueadero();
		
		if(vehiculosEnParqueaderoList == null || vehiculosEnParqueaderoList.isEmpty()) {
			fail();
		}
		
		Assert.assertEquals(vehiculoRegistroInDto.getPlaca(), vehiculosEnParqueaderoList.get(0).getPlaca());
	}

	@Test
	public void consultarVehiculoEnParqueaderoTest() {

	}
	
	@Test
	public void registrarVehiculoEnParqueaderoTest() {

	}
	
	@Test
	public void registrarSalidaVehiculoDeParqueaderoTest() {

	}
}
