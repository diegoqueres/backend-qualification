package net.diegoqueres.backendqualification.services;

import static net.diegoqueres.backendqualification.constants.ExternalConstants.GEOCODING_API_KEY;
import static net.diegoqueres.backendqualification.constants.ExternalConstants.GEOCODING_URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.diegoqueres.backendqualification.dtos.AddressDTO;
import net.diegoqueres.backendqualification.entities.Address;
import net.diegoqueres.backendqualification.entities.City;
import net.diegoqueres.backendqualification.entities.Country;
import net.diegoqueres.backendqualification.entities.State;
import net.diegoqueres.backendqualification.repositories.AddressRepository;
import net.diegoqueres.backendqualification.repositories.CityRepository;
import net.diegoqueres.backendqualification.repositories.CountryRepository;
import net.diegoqueres.backendqualification.repositories.StateRepository;
import net.diegoqueres.backendqualification.resources.exceptions.ValidationException;
import net.diegoqueres.backendqualification.services.exceptions.DatabaseException;
import net.diegoqueres.backendqualification.services.exceptions.ResourceNotFoundException;

/**
 * Classe Service da entidade Address (Endereço).
 * 
 * @author Diego Queres
 *
 */
@Service
public class AddressService {
	private static final Logger log = LoggerFactory.getLogger(AddressService.class);

	@Autowired
	private AddressRepository repository;

	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private CountryRepository countryRepo;

	private final RestTemplate restTemplate;

	private ObjectMapper objectMapper;

	/**
	 * Construtor da classe AddressService.
	 * 
	 * @param restTemplateBuilder
	 */
	public AddressService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		this.objectMapper = new ObjectMapper();
		configObjectMapper();
	}

	private void configObjectMapper() {
		this.objectMapper = this.objectMapper.disable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
				.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
	}

	public List<Address> findAll() {
		return repository.findAll();
	}

	public Address findById(Integer id) {
		Optional<Address> obj = repository.findById(id);
		return obj.get();
	}

	public Address insert(Address entity) {
		if (entity.getLatitude() == null && entity.getLongitude() == null)
			entity = configEmptyGeometryLocation(entity);

		return repository.save(entity);
	}

	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Address update(Integer id, Address obj) {
		try {
			Address entity = repository.getOne(id);
			updateData(entity, obj);

			if (entity.getLatitude() == null && entity.getLongitude() == null)
				entity = configEmptyGeometryLocation(entity);

			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public List<Address> findAllByCountryId(Integer countryId) {
		return repository.findAllByCountryId(countryId);
	}

	private void updateData(Address entity, Address obj) {
		entity.setStreetName(obj.getStreetName());
		entity.setNumber(obj.getNumber());
		entity.setComplement(obj.getComplement());
		entity.setNeighbourhood(obj.getNeighbourhood());
		entity.setCity(obj.getCity());
		entity.setState(obj.getState());
		entity.setCountry(obj.getCountry());
		entity.setZipcode(obj.getZipcode());
		entity.setLatitude(obj.getLatitude());
		entity.setLongitude(obj.getLongitude());
	}

	private Address configEmptyGeometryLocation(Address entity) {
		try {
			var url = String.format(GEOCODING_URL, entity.getNumber(), entity.getStreetName(),
					entity.getNeighbourhood(), entity.getCity().getName(), entity.getCountry().getName(),
					GEOCODING_API_KEY);
			var response = this.restTemplate.getForObject(url, String.class);
			JsonNode root = objectMapper.readTree(response);

			var resultsNode = root.path("results");
			if (resultsNode.isArray()) {
				for (JsonNode node : resultsNode) {
					var geometryNode = node.path("geometry");
					if (!geometryNode.isMissingNode()) {
						var location = geometryNode.path("location");
						if (!location.isMissingNode()) {
							var lat = location.path("lat");
							var lng = location.path("lng");
							if (!lat.isMissingNode()) {
								log.info("Latitude detectada: {}", lat.asDouble());
								entity.setLatitude(lat.asDouble());
							}
							if (!lng.isMissingNode()) {
								log.info("Longitude detectada: {}", lng.asDouble());
								entity.setLongitude(lng.asDouble());
							}
						}
					}
				}
			}

		} catch (RestClientException e) {
			log.error("Erro ao acessar a API de Geocoding do Google: {}", e.getLocalizedMessage());
		} catch (JsonProcessingException e) {
			log.error("Erro ao processar JSON retornado da API de Geocoding do Google: {}", e.getLocalizedMessage());
		}

		return entity;

	}

	public Address fromDto(AddressDTO dto) {
		Optional<City> city = cityRepo.findById(dto.getCity());
		Optional<State> state = stateRepo.findById(dto.getState());
		Optional<Country> country = countryRepo.findById(dto.getCountry());

		validateMandatoryEntities(city, state, country);

		return new Address(dto.getStreetName(), dto.getNumber(), dto.getComplement(), dto.getNeighbourhood(), city,
				state, country, dto.getZipcode(), dto.getLatitude(), dto.getLongitude());

	}

	private void validateMandatoryEntities(Optional<City> city, Optional<State> state, Optional<Country> country) {
		List<ObjectError> errorList = new ArrayList<>();
		if (city.isEmpty())
			errorList.add(new ObjectError("City", "City not found"));
		if (state.isEmpty())
			errorList.add(new ObjectError("State", "State not found"));
		if (country.isEmpty())
			errorList.add(new ObjectError("Country", "Country not found"));

		if (!errorList.isEmpty()) {
			throw new ValidationException(errorList);
		}

	}

}
