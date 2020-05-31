package net.diegoqueres.backendqualification.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import net.diegoqueres.backendqualification.entities.Address;
import net.diegoqueres.backendqualification.repositories.AddressRepository;
import net.diegoqueres.backendqualification.services.exceptions.DatabaseException;
import net.diegoqueres.backendqualification.services.exceptions.ResourceNotFoundException;

/**
 * Classe Service da entidade Address (Endereço).
 * 
 * @author diego
 *
 */
@Service
public class AddressService {

	@Autowired
	private AddressRepository repository;

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

}
