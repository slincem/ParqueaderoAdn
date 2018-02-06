package co.ceiba.parqueadero.modelo.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.ceiba.parqueadero.modelo.enums.TipoVehiculo;

@Entity
@Table(name="vehiculo")
public class Vehiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_vehiculo", updatable = false, nullable = false)
	protected Long id;

	@Column(unique=true, nullable=false)
	protected String placa;

	@Column(nullable=false)
	protected int cilindraje;

	@Enumerated(EnumType.STRING)
	protected TipoVehiculo tipoVehiculo;

	@OneToMany(mappedBy = "vehiculo", fetch = FetchType.LAZY)
	private List<HistoricoParqueadero> listaHistoricoParqueadero;
	
	public Vehiculo() {
	}

	public Vehiculo(String placa, int cilindraje, TipoVehiculo tipoVehiculo) {
		super();
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.tipoVehiculo = tipoVehiculo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public List<HistoricoParqueadero> getListaHistoricoParqueadero() {
		return listaHistoricoParqueadero;
	}

	public void setListaHistoricoParqueadero(List<HistoricoParqueadero> listaHistoricoParqueadero) {
		this.listaHistoricoParqueadero = listaHistoricoParqueadero;
	}

}
