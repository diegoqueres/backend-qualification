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
import org.springframework.web.bind.annotation.RestController;

import net.diegoqueres.backendqualification.entities.Country;
import net.diegoqueres.backendqualification.services.CountryService;

/**
 * Controller para recuperar dados da entidade de Países. Basicamente oferece
 * suporte para consultar a base de dados de países, para consultar seus
 * códigos, que são usados no cadastro de endereços.
 * 
 * @author diego
 *
 */
@RestController
@RequestMapping("api/v1/countries")
@CrossOrigin(origins = "*")
public class CountryResource {
	private static final Logger log = LoggerFactory.getLogger(CountryResource.class);

	@Autowired
	private CountryService countryService;

	/**
	 * Retorna a lista de todos os países.
	 * 
	 * @return ResponseEntity<List<Country>>
	 */
	@GetMapping
	public ResponseEntity<List<Country>> findAll() {
		log.info("Recuperando lista de todos os países");
		List<Country> list = countryService.findAll();
		return ResponseEntity.ok().body(list);
	}

	/**
	 * Retorna um país por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Country>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Country> findById(@PathVariable Integer id) {
		log.info("Recuperando país com id {}", id);
		Country obj = countryService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
