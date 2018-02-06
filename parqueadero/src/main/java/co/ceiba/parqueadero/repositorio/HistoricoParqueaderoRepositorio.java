package co.ceiba.parqueadero.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.modelo.dtos.out.VehiculoEnParqueaderoOutDTO;
import co.ceiba.parqueadero.modelo.entidades.HistoricoParqueadero;

@Repository
public interface HistoricoParqueaderoRepositorio extends JpaRepository<HistoricoParqueadero, Long> {

	@Query("SELECT new co.ceiba.parqueadero.modelo.dtos.out.VehiculoEnParqueaderoOutDTO(hparq.fechaIngreso, "
			+ "hparq.fechaSalida, veh.placa, veh.cilindraje, veh.tipoVehiculo)"
			+ " FROM HistoricoParqueadero hparq JOIN hparq.vehiculo veh "
			+ "WHERE hparq.estado = co.ceiba.parqueadero.modelo.enums.Estado.ACTIVO")
	public List<VehiculoEnParqueaderoOutDTO> consultarVehiculosEnParqueadero();

	@Query("SELECT new co.ceiba.parqueadero.modelo.dtos.out.VehiculoEnParqueaderoOutDTO(hparq.fechaIngreso,"
			+ " hparq.fechaSalida, veh.placa, veh.cilindraje, veh.tipoVehiculo) FROM HistoricoParqueadero "
			+ "hparq JOIN hparq.vehiculo veh "
			+ "WHERE veh.placa = :placa AND hparq.estado = co.ceiba.parqueadero.modelo.enums.Estado.ACTIVO")
	public VehiculoEnParqueaderoOutDTO consultarVehiculoEnParqueadero(@Param("placa") String placa);

	@Query("SELECT hparq FROM HistoricoParqueadero hparq JOIN hparq.vehiculo veh "
			+ "WHERE veh.placa = :placa AND hparq.estado = co.ceiba.parqueadero.modelo.enums.Estado.ACTIVO")
	public HistoricoParqueadero consultarHistoricoParqueaderoVehiculoEnParqueadero(@Param("placa") String placa);

	@Modifying
	@Transactional
	@Query("UPDATE HistoricoParqueadero hparq set hparq.fechaSalida = :fechaSalida, "
			+ "hparq.estado = co.ceiba.parqueadero.modelo.enums.Estado.INACTIVO "
			+ "WHERE hparq.id = :id")
	public void actualizarHistoricoDeSalidaVehiculo(@Param("id") Long id, @Param("fechaSalida") Date fechaSalida);

}
