package co.ceiba.parqueadero.servicio.implementaciones;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.velocity.exception.ParseErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.parqueadero.modelo.dtos.in.VehiculoRegistroInDTO;
import co.ceiba.parqueadero.modelo.dtos.in.VehiculoRegistroSalidaInDTO;
import co.ceiba.parqueadero.modelo.dtos.out.VehiculoEnParqueaderoOutDTO;
import co.ceiba.parqueadero.modelo.entidades.HistoricoParqueadero;
import co.ceiba.parqueadero.modelo.entidades.Vehiculo;
import co.ceiba.parqueadero.modelo.enums.Estado;
import co.ceiba.parqueadero.modelo.enums.TipoVehiculo;
import co.ceiba.parqueadero.repositorio.HistoricoParqueaderoRepositorio;
import co.ceiba.parqueadero.repositorio.VehiculoRepositorio;
import co.ceiba.parqueadero.servicio.VigilanteServicio;
import co.ceiba.parqueadero.servicio.entidadesnegocio.Parqueadero;
import co.ceiba.parqueadero.servicio.excepciones.ExcepcionNegocio;
import co.ceiba.parqueadero.utilitario.FechaUtilitario;
import co.ceiba.parqueadero.utilitario.constantes.ConstantesExpresionesRegulares;
import co.ceiba.parqueadero.utilitario.constantes.MensajesError;

@Service
public class VigilanteServicioImpl implements VigilanteServicio {

	@Autowired
	private HistoricoParqueaderoRepositorio historicoParqueaderoRepositorio;

	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;

	@Override
	public List<VehiculoEnParqueaderoOutDTO> consultarVehiculosEnParqueadero() {
		return historicoParqueaderoRepositorio.consultarVehiculosEnParqueadero();
	}

	@Override
	public VehiculoEnParqueaderoOutDTO consultarVehiculoEnParqueadero(String placa) {
		placa = placa.toUpperCase();
		VehiculoEnParqueaderoOutDTO vehiculoEnParqueaderoOutDTO = historicoParqueaderoRepositorio
				.consultarVehiculoEnParqueadero(placa);
		if (vehiculoEnParqueaderoOutDTO != null && vehiculoEnParqueaderoOutDTO.getFechaIngreso() != null) {
			Vehiculo vehiculo = vehiculoRepositorio.findByPlaca(placa);
			Date fechaIngresoDate;
			try {
				fechaIngresoDate = FechaUtilitario.formatearStringAFecha(vehiculoEnParqueaderoOutDTO.getFechaIngreso());
			} catch (ParseException e) {
				throw new ParseErrorException(e.getMessage());
			}

			Double valorAPagar = calcularValorAPagarEnParqueadero(fechaIngresoDate, new Date(),
					vehiculo.getTipoVehiculo(), vehiculo.getCilindraje());
			vehiculoEnParqueaderoOutDTO.setValorAPagar(valorAPagar);
		}

		return vehiculoEnParqueaderoOutDTO;
	}

	@Override
	public boolean registrarVehiculoEnParqueadero(VehiculoRegistroInDTO vehiculoRegistroInDto) {
		
		validarDatosDeRegistro(vehiculoRegistroInDto);
		
		if (consultarVehiculoEnParqueadero(vehiculoRegistroInDto.getPlaca()) != null) {
			throw new ExcepcionNegocio(MensajesError.VEHICULO_YA_ESTA_EN_PARQUEADERO);
		}
		
		vehiculoRegistroInDto.setPlaca(vehiculoRegistroInDto.getPlaca().toUpperCase());

		if (!esPosibleEntradaDelVehiculoSegunPlaca(vehiculoRegistroInDto.getPlaca(), new Date())) {
			throw new ExcepcionNegocio(MensajesError.PLACA_DE_VEHICULO_NO_PERMITIDA_ESTE_DIA);
		}

		Vehiculo vehiculo;
		if (!comprobarExistenciaDeVehiculo(vehiculoRegistroInDto.getPlaca())) {
			vehiculo = registrarVehiculoPorPrimeraVez(vehiculoRegistroInDto);
		} else {
			vehiculo = actualizarDatosVehiculo(vehiculoRegistroInDto);
		}

		if (!verificarCupoParaVehiculo(vehiculo.getTipoVehiculo())) {
			throw new ExcepcionNegocio((MensajesError.NO_HAY_DISPONIBILIDAD_DE_CUPO));
		}

		Vehiculo vehiculoAHistorico = vehiculoRepositorio.findByPlaca(vehiculo.getPlaca());
		HistoricoParqueadero historicoParqueadero = construirHistoricoParqueadero(vehiculoAHistorico);
		historicoParqueaderoRepositorio.save(historicoParqueadero);

		return true;
	}

	@Override
	public Double registrarSalidaVehiculoDeParqueadero(VehiculoRegistroSalidaInDTO vehiculoRegistroSalidaInDto) {

		if (consultarVehiculoEnParqueadero(vehiculoRegistroSalidaInDto.getPlaca()) == null) {
			throw new ExcepcionNegocio(MensajesError.VEHICULO_NO_ESTA_EN_PARQUEADERO);
		}
		
		vehiculoRegistroSalidaInDto.setPlaca(vehiculoRegistroSalidaInDto.getPlaca().toUpperCase());

		HistoricoParqueadero historicoParqueadero = historicoParqueaderoRepositorio
				.consultarHistoricoParqueaderoVehiculoEnParqueadero(vehiculoRegistroSalidaInDto.getPlaca());

		if (historicoParqueadero == null) {
			throw new ExcepcionNegocio(MensajesError.HISTORICO_DE_REGISTRO_DEL_VEHICULO_INEXISTENTE);
		}

		Date fechaSalida = new Date();
		Vehiculo vehiculo = vehiculoRepositorio.findOne(historicoParqueadero.getVehiculo().getId());
		Double valorAPagar = calcularValorAPagarEnParqueadero(historicoParqueadero.getFechaIngreso(), fechaSalida,
				vehiculo.getTipoVehiculo(), vehiculo.getCilindraje());

		historicoParqueaderoRepositorio.actualizarHistoricoDeSalidaVehiculo(historicoParqueadero.getId(), fechaSalida,
				valorAPagar);
		return valorAPagar;
	}

	public Vehiculo registrarVehiculoPorPrimeraVez(VehiculoRegistroInDTO vehiculoRegistroInDto) {
		Vehiculo vehiculo = convertirVehiculoRegistroInDtoAVehiculo(vehiculoRegistroInDto);
		vehiculoRepositorio.save(vehiculo);

		return vehiculo;
	}

	public Vehiculo actualizarDatosVehiculo(VehiculoRegistroInDTO vehiculoRegistroInDto) {

		Vehiculo vehiculo = convertirVehiculoRegistroInDtoAVehiculo(vehiculoRegistroInDto);

		vehiculoRepositorio.update(vehiculo.getPlaca(), vehiculo.getCilindraje(), vehiculo.getTipoVehiculo());

		return vehiculo;
	}

	public Vehiculo convertirVehiculoRegistroInDtoAVehiculo(VehiculoRegistroInDTO vehiculoRegistroInDto) {
		Vehiculo vehiculo;
		if (vehiculoRegistroInDto.getTipoVehiculo().equals(TipoVehiculo.CARRO.getValue())) {
			vehiculo = new Vehiculo(vehiculoRegistroInDto.getPlaca(), vehiculoRegistroInDto.getCilindraje(),
					TipoVehiculo.CARRO);
		} else {
			vehiculo = new Vehiculo(vehiculoRegistroInDto.getPlaca(), vehiculoRegistroInDto.getCilindraje(),
					TipoVehiculo.MOTO);
		}

		return vehiculo;
	}

	public boolean comprobarExistenciaDeVehiculo(String placa) {

		Vehiculo vehiculoEncontrado = vehiculoRepositorio.findByPlaca(placa);
		return vehiculoEncontrado != null;
	}

	public boolean empiezaPorLetraRegistrinda(String placa) {
		return placa.charAt(0) == Parqueadero.LETRA_RESTRINGIDA;
	}

	public boolean verificarDiasDeRestriccionDeLetra(Date fechaActual) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaActual);
		int diaDeHoy = calendar.get(Calendar.DAY_OF_WEEK);
		return diaDeHoy == Calendar.SUNDAY || diaDeHoy == Calendar.MONDAY;
	}

	public boolean esPosibleEntradaDelVehiculoSegunPlaca(String placa, Date fechaActual) {
		return (empiezaPorLetraRegistrinda(placa) && verificarDiasDeRestriccionDeLetra(fechaActual))
				|| !(empiezaPorLetraRegistrinda(placa));
	}

	public boolean verificarCupoParaVehiculo(TipoVehiculo tipoVehiculo) {
		if (tipoVehiculo.equals(TipoVehiculo.CARRO)) {
			return historicoParqueaderoRepositorio
					.contarVehiculosEnParqueadero(tipoVehiculo) < Parqueadero.CANTIDAD_MAXIMA_DE_CARROS_ADMITIDOS;
		} else {
			return historicoParqueaderoRepositorio
					.contarVehiculosEnParqueadero(tipoVehiculo) < Parqueadero.CANTIDAD_MAXIMA_DE_MOTOS_ADMITIDAS;
		}
	}

	public double calcularValorAPagarEnParqueadero(Date fechaIngreso, Date fechaSalida, TipoVehiculo tipoVehiculo,
			int cilindraje) {

		long diasParqueado = FechaUtilitario.calcularDiferenciaEnDiasEntreFechas(fechaIngreso, fechaSalida);
		long horasParqueado = FechaUtilitario.calcularDiferenciaEnHorasEntreFechas(fechaIngreso, fechaSalida);

		if (horasParqueado >= Parqueadero.HORAS_INICIO_PARA_COBRAR_POR_DIA) {
			diasParqueado++;
			horasParqueado = (long) (horasParqueado - Parqueadero.HORAS_INICIO_PARA_COBRAR_POR_DIA);
		}

		horasParqueado++;

		if (tipoVehiculo.equals(TipoVehiculo.CARRO)) {
			return (diasParqueado * Parqueadero.VALOR_DIA_CARRO) + (horasParqueado * Parqueadero.VALOR_HORA_CARRO);
		} else {
			int recargoPorCilindraje = 0;
			if (cilindraje > Parqueadero.CILINDRAJE_PARA_RECARGO) {
				recargoPorCilindraje = 2000;
			}
			return (diasParqueado * Parqueadero.VALOR_DIA_MOTO) + (horasParqueado * Parqueadero.VALOR_HORA_MOTO)
					+ recargoPorCilindraje;
		}
	}

	public void validarDatosDeRegistro(VehiculoRegistroInDTO vehiculoRegistroInDto) {
		if (vehiculoRegistroInDto.getTipoVehiculo() == null
				|| (!vehiculoRegistroInDto.getTipoVehiculo().equals(TipoVehiculo.CARRO.getValue())
						&& !vehiculoRegistroInDto.getTipoVehiculo().equals(TipoVehiculo.MOTO.getValue()))) {
			throw new ExcepcionNegocio(MensajesError.TIPO_VEHICULO_NO_VALIDO);
		}

		if (vehiculoRegistroInDto.getPlaca() == null
				|| !vehiculoRegistroInDto.getPlaca().matches(ConstantesExpresionesRegulares.REGEX_PLACA)) {
			throw new ExcepcionNegocio(MensajesError.PLACA_NO_VALIDA);
		}
		
		if(vehiculoRegistroInDto.getCilindraje() == 0) {
			throw new ExcepcionNegocio(MensajesError.CILINDRAJE_NO_VALIDO);
		}
	}

	public HistoricoParqueadero construirHistoricoParqueadero(Vehiculo vehiculo) {
		Date fechaIngreso = new Date();

		HistoricoParqueadero historicoParqueadero = new HistoricoParqueadero();
		historicoParqueadero.setFechaIngreso(fechaIngreso);
		historicoParqueadero.setVehiculo(vehiculo);
		historicoParqueadero.setEstado(Estado.ACTIVO);

		return historicoParqueadero;

	}

}
