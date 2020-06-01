package net.diegoqueres.backendqualification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.diegoqueres.backendqualification.entities.ApplicationUser;

/**
 * Repositório da entidade ApplicationUser (Usuário).
 * 
 * @author Diego Queres
 *
 */
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Integer> {
	ApplicationUser findByEmail(String email);
}
