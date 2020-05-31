package net.diegoqueres.backendqualification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.diegoqueres.backendqualification.entities.Country;

/**
 * Respositório da entidade Country (País).
 * 
 * @author diego
 *
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
