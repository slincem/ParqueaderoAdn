package co.ceiba.parqueadero.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.ceiba.parqueadero.modelo.entidades.Vehiculo;
import co.ceiba.parqueadero.modelo.enums.TipoVehiculo;

@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Long> {

	@Query("SELECT v FROM Vehiculo v WHERE v.placa = :placa")
	public Vehiculo findByPlaca(@Param("placa") String placa);

	@Modifying
	@Transactional
	@Query("UPDATE Vehiculo v set v.cilindraje = :cilindraje, v.tipoVehiculo = :tipoVehiculo WHERE v.placa = :placa")
	public void update(@Param("placa") String placa, @Param("cilindraje") int cilindraje,
			@Param("tipoVehiculo") TipoVehiculo tipoVehiculo);

}
