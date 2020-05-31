package net.diegoqueres.backendqualification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.diegoqueres.backendqualification.entities.Address;

/**
 * Respositório da entidade Address (Endereço).
 * 
 * @author diego
 *
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
