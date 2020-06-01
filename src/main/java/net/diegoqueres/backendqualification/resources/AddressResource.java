package net.diegoqueres.backendqualification.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.diegoqueres.backendqualification.dtos.AddressDTO;
import net.diegoqueres.backendqualification.entities.Address;
import net.diegoqueres.backendqualification.resources.exceptions.ValidationException;
import net.diegoqueres.backendqualification.services.AddressService;

/**
 * Controller para CRUD da entidade Address (Endereço).
 * 
 * @author Diego Queres
 *
 */
@RestController
@RequestMapping("api/v1/addresses")
@CrossOrigin(origins = "*")
public class AddressResource {
	private static final Logger log = LoggerFactory.getLogger(AddressResource.class);

	@Autowired
	public AddressService addressService;

	/**
	 * Retorna a lista de todos os endereços.
	 * 
	 * @return ResponseEntity<List<AddressDTO>>
	 */
	@GetMapping
	public ResponseEntity<List<AddressDTO>> findAll() {
		log.info("Recuperando lista de todos os endereços");
		List<Address> list = addressService.findAll();
		List<AddressDTO> listDto = list.stream().map(x -> new AddressDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	/**
	 * Retorna um endereço com ID.
	 * 
	 * @param id Código do endereço na API.
	 * @return ResponseEntity<City>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<AddressDTO> findById(@PathVariable Integer id) {
		log.info("Recuperando endereço com id {}", id);
		Address obj = addressService.findById(id);
		AddressDTO objDto = new AddressDTO(obj);
		return ResponseEntity.ok().body(objDto);
	}

	/**
	 * Cadastra um novo endereço.
	 * 
	 * @param addressDto Dto com os dados do endereço a ser cadastrado.
	 * @param result     Armazena erros da validação dos dados de entrada.
	 */
	@PostMapping
	public ResponseEntity<AddressDTO> insert(@Valid @RequestBody AddressDTO addressDto, BindingResult result) {
		log.info("Salvando novo endereço {}", addressDto);
		if (result.hasErrors()) {
			throw new ValidationException(result.getAllErrors());
		}

		Address obj = addressService.fromDto(addressDto);
		obj = addressService.insert(obj);
		AddressDTO newAddressDto = new AddressDTO(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).body(newAddressDto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		addressService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<AddressDTO> update(@PathVariable Integer id, @Valid @RequestBody AddressDTO addressDto,
			BindingResult result) {
		Address address = addressService.fromDto(addressDto);
		addressService.update(id, address);
		return ResponseEntity.ok().body(addressDto);
	}

}
