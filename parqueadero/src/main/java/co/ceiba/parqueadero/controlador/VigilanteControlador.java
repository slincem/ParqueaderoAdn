package co.ceiba.parqueadero.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parqueadero")
public class VigilanteControlador {
	
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	  public List getShops() {
		List lista = new ArrayList<>();
		lista.add(1);
	    return lista;
	  }

}
