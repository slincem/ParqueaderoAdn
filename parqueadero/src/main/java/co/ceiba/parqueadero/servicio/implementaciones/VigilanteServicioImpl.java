package co.ceiba.parqueadero.servicio.implementaciones;

import java.util.Date;
import java.util.List;

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
import co.ceiba.parqueadero.servicio.excepciones.ExcepcionNegocio;
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

		return historicoParqueaderoRepositorio.consultarVehiculoEnParqueadero(placa);
	}

	@Override
	public boolean registrarVehiculoEnParqueadero(VehiculoRegistroInDTO vehiculoRegistroInDto) {

		if (consultarVehiculoEnParqueadero(vehiculoRegistroInDto.getPlaca()) != null) {
			throw new ExcepcionNegocio(MensajesError.VEHICULO_YA_ESTA_EN_PARQUEADERO);
		}

		Vehiculo vehiculo;
		if (!comprobarExistenciaDeVehiculo(vehiculoRegistroInDto.getPlaca())) {
			vehiculo = registrarVehiculoPorPrimeraVez(vehiculoRegistroInDto);
		} else {
			vehiculo = actualizarDatosVehiculo(vehiculoRegistroInDto);
		}

		Vehiculo vehiculoAHistorico = vehiculoRepositorio.findByPlaca(vehiculo.getPlaca());
		HistoricoParqueadero historicoParqueadero = construirHistoricoParqueadero(vehiculoAHistorico);
		historicoParqueaderoRepositorio.save(historicoParqueadero);

		return true;
	}

	@Override
	public boolean registrarSalidaVehiculoDeParqueadero(VehiculoRegistroSalidaInDTO vehiculoRegistroSalidaInDto) {

		if (consultarVehiculoEnParqueadero(vehiculoRegistroSalidaInDto.getPlaca()) == null) {
			throw new ExcepcionNegocio(MensajesError.VEHICULO_NO_ESTA_EN_PARQUEADERO);
		}

		HistoricoParqueadero historicoParqueadero = historicoParqueaderoRepositorio
				.consultarHistoricoParqueaderoVehiculoEnParqueadero(vehiculoRegistroSalidaInDto.getPlaca());
		
		if(historicoParqueadero == null) {
			throw new ExcepcionNegocio(MensajesError.VEHICULO_NO_ESTA_EN_PARQUEADERO);
		}
		return false;
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

	@Override
	public boolean comprobarExistenciaDeVehiculo(String placa) {

		Vehiculo vehiculoEncontrado = vehiculoRepositorio.findByPlaca(placa);
		return vehiculoEncontrado != null;
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
