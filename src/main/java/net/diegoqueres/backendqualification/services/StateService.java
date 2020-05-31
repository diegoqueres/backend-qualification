package net.diegoqueres.backendqualification.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.diegoqueres.backendqualification.entities.Country;
import net.diegoqueres.backendqualification.entities.State;
import net.diegoqueres.backendqualification.repositories.StateRepository;

/**
 * Classe Service da entidade State (Estado).
 * 
 * @author diego
 *
 */
@Service
public class StateService {

	@Autowired
	private StateRepository repository;

	public List<State> findAll() {
		return repository.findAll();
	}

	public State findById(Integer id) {
		Optional<State> obj = repository.findById(id);
		return obj.get();
	}

	public List<State> findAllByCountry(Country country) {
		return repository.findAllByCountry(country);
	}

}
