package net.diegoqueres.backendqualification.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.diegoqueres.backendqualification.entities.City;
import net.diegoqueres.backendqualification.entities.State;
import net.diegoqueres.backendqualification.repositories.CityRepository;

/**
 * Classe Service da entidade City (Cidade).
 * 
 * @author diego
 *
 */
@Service
public class CityService {

	@Autowired
	private CityRepository repository;

	public List<City> findAll() {
		return repository.findAll();
	}

	public City findById(Integer id) {
		Optional<City> obj = repository.findById(id);
		return obj.get();
	}

	public List<City> findAllByState(State state) {
		return repository.findAllByState(state);
	}

}
