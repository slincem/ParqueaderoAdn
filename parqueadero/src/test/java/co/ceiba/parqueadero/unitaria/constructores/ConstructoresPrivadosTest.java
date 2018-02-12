package co.ceiba.parqueadero.unitaria.constructores;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.parqueadero.servicio.entidadesnegocio.Parqueadero;
import co.ceiba.parqueadero.utilitario.FechaUtilitario;
import co.ceiba.parqueadero.utilitario.constantes.ConstantesExpresionesRegulares;
import co.ceiba.parqueadero.utilitario.constantes.ConstantesNumeros;
import co.ceiba.parqueadero.utilitario.constantes.MensajesError;

@RunWith(SpringRunner.class)
public class ConstructoresPrivadosTest {

	@Test
	public void parqueaderoConstructorPrivadoTest()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor<Parqueadero> constructor = Parqueadero.class.getDeclaredConstructor();
		Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void mensajesErrorConstructorPrivadoTest()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor<MensajesError> constructor = MensajesError.class.getDeclaredConstructor();
		Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}
	
	@Test
	public void constantesNumerosConstructorPrivadoTest()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor<ConstantesNumeros> constructor = ConstantesNumeros.class.getDeclaredConstructor();
		Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}
	
	@Test
	public void constantesExpresionesRegularesConstructorPrivadoTest()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor<ConstantesExpresionesRegulares> constructor = ConstantesExpresionesRegulares.class.getDeclaredConstructor();
		Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}
	
	@Test
	public void fechaUtilitarioConstructorPrivadoTest()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor<FechaUtilitario> constructor = FechaUtilitario.class.getDeclaredConstructor();
		Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}
}
