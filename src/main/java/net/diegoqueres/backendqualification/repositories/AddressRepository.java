package net.diegoqueres.backendqualification.repositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.diegoqueres.backendqualification.entities.Address;

/**
 * Repositório da entidade Address (Endereço).
 * 
 * @author Diego Queres
 *
 */
@Repository
@NamedQueries(
		@NamedQuery(name = "AddressRepository.findAllByCountryId",
					query = "SELECT addr.* FROM `addresses` addr WHERE addr.country_id = :countryId")
)
public interface AddressRepository extends JpaRepository<Address, Integer> {
	List<Address> findAllByCountryId(@Param("countryId") Integer countryId);
}
