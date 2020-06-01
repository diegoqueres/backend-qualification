package net.diegoqueres.backendqualification.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.diegoqueres.backendqualification.entities.City;
import net.diegoqueres.backendqualification.entities.State;
import net.diegoqueres.backendqualification.services.CityService;
import net.diegoqueres.backendqualification.services.StateService;

/**
 * Controller para recuperar dados da entidade de Cidades. Basicamente oferece
 * suporte para consultar a base de dados de cidades, para consultar seus
 * códigos, que são usados no cadastro de endereços.
 * 
 * @author diego
 *
 */
@RestController
@RequestMapping("api/v1/cities")
@CrossOrigin(origins = "*")
public class CityResource {
	private static final Logger log = LoggerFactory.getLogger(CityResource.class);

	@Autowired
	private CityService cityService;
	
	@Autowired
	private StateService stateService;

	/**
	 * Retorna a lista de todas as cidades.
	 * 
	 * @return ResponseEntity<List<City>>
	 */
	@GetMapping
	public ResponseEntity<List<City>> findAll() {
		log.info("Recuperando lista de todas as cidades");
		List<City> list = cityService.findAll();
		return ResponseEntity.ok().body(list);
	}

	/**
	 * Retorna uma cidade por ID.
	 * 
	 * @param id Código da Cidade na API.
	 * @return ResponseEntity<City>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<City> findById(@PathVariable Integer id) {
		log.info("Recuperando cidade com id {}", id);
		City obj = cityService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	/**
	 * Retorna uma lista de cidades por código de estado.
	 * 
	 * @param stateId Código do Estado na API.
	 * @return ResponseEntity<List<City>>
	 */
	@GetMapping(value = "/search")
	public ResponseEntity<List<City>> findAllByState(
			@RequestParam(value = "state", defaultValue = "") Integer stateId) {
		log.info("Recuperando lista de cidades do estado com ID {}", stateId);
		State state = stateService.findById(stateId);
		List<City> list = cityService.findAllByState(state);
		return ResponseEntity.ok().body(list);
	}

}
