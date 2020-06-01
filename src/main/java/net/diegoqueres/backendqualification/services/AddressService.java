package net.diegoqueres.backendqualification.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

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

	@Autowired
	private AddressRepository repository;

	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private CountryRepository countryRepo;

	public List<Address> findAll() {
		return repository.findAll();
	}

	public Address findById(Integer id) {
		Optional<Address> obj = repository.findById(id);
		return obj.get();
	}

	public Address insert(Address obj) {
		return repository.save(obj);
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
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
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
