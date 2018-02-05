package co.ceiba.parqueadero.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.ceiba.parqueadero.modelo.dtos.out.VehiculoEnParqueaderoOutDTO;
import co.ceiba.parqueadero.modelo.entidades.HistoricoParqueadero;

@Repository
public interface HistoricoParqueaderoRepositorio extends JpaRepository<HistoricoParqueadero, Long> {

	@Query("SELECT new co.ceiba.parqueadero.modelo.dtos.out.VehiculoEnParqueaderoOutDTO(hparq.fechaIngreso, hparq.fechaSalida, veh.placa, veh.cilindraje) FROM HistoricoParqueadero hparq JOIN hparq.vehiculo veh")
	public List<VehiculoEnParqueaderoOutDTO> consultarVehiculosEnParqueadero();
	
	@Query("SELECT new co.ceiba.parqueadero.modelo.dtos.out.VehiculoEnParqueaderoOutDTO(hparq.fechaIngreso, hparq.fechaSalida, veh.placa, veh.cilindraje) FROM HistoricoParqueadero hparq JOIN hparq.vehiculo veh WHERE veh.placa = :placa")
	public VehiculoEnParqueaderoOutDTO consultarVehiculoEnParqueadero(@Param("placa") String placa);

}
