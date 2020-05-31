package net.diegoqueres.backendqualification.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.diegoqueres.backendqualification.entities.Country;
import net.diegoqueres.backendqualification.repositories.CountryRepository;

/**
 * Classe Service da entidade Country (País).
 * 
 * @author diego
 *
 */
@Service
public class CountryService {

	@Autowired
	private CountryRepository repository;

	public List<Country> findAll() {
		return repository.findAll();
	}

	public Country findById(Integer id) {
		Optional<Country> obj = repository.findById(id);
		return obj.get();
	}

}
