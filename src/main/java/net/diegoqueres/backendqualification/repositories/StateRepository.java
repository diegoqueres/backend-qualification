package net.diegoqueres.backendqualification.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.diegoqueres.backendqualification.entities.Country;
import net.diegoqueres.backendqualification.entities.State;

/**
 * Respositório da entidade State (Estado).
 * 
 * @author diego
 *
 */
@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

	@Transactional(readOnly = true)
	public List<State> findAllByCountry(Country country);

}
