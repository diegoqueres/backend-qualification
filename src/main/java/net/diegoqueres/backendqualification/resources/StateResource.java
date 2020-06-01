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

import net.diegoqueres.backendqualification.entities.Country;
import net.diegoqueres.backendqualification.entities.State;
import net.diegoqueres.backendqualification.services.CountryService;
import net.diegoqueres.backendqualification.services.StateService;

/**
 * Controller para recuperar dados da entidade de Estados. Basicamente oferece
 * suporte para consultar a base de dados de estados, para consultar seus
 * códigos, que são usados no cadastro de endereços.
 * 
 * @author Diego Queres
 *
 */
@RestController
@RequestMapping("api/v1/states")
@CrossOrigin(origins = "*")
public class StateResource {
	private static final Logger log = LoggerFactory.getLogger(StateResource.class);

	@Autowired
	private StateService stateService;

	@Autowired
	private CountryService countryService;

	/**
	 * Retorna a lista de todos os estados.
	 * 
	 * @return ResponseEntity<List<State>>
	 */
	@GetMapping
	public ResponseEntity<List<State>> findAll() {
		log.info("Recuperando lista de todos os estados");
		List<State> list = stateService.findAll();
		return ResponseEntity.ok().body(list);
	}

	/**
	 * Retorna um estado por ID.
	 * 
	 * @param id Código do Estado na API.
	 * @return ResponseEntity<State>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<State> findById(@PathVariable Integer id) {
		log.info("Recuperando estado com id {}", id);
		State obj = stateService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	/**
	 * Retorna uma lista de estados por código de país.
	 * 
	 * @param countryId Código do país na API.
	 * @return ResponseEntity<List<State>>
	 */
	@GetMapping(value = "/search")
	public ResponseEntity<List<State>> findAllByCountry(
			@RequestParam(value = "country", defaultValue = "") Integer countryId) {
		log.info("Recuperando lista de estados do país com ID {}", countryId);
		Country country = countryService.findById(countryId);
		List<State> list = stateService.findAllByCountry(country);
		return ResponseEntity.ok().body(list);
	}

}
