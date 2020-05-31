package net.diegoqueres.backendqualification.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.diegoqueres.backendqualification.entities.City;
import net.diegoqueres.backendqualification.entities.State;

/**
 * Repositório da entidade City (Cidade).
 * 
 * @author diego
 *
 */
@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

	@Transactional(readOnly = true)
	public List<City> findAllByState(State state);

}
