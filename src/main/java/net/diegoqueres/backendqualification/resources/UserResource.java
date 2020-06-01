package net.diegoqueres.backendqualification.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.diegoqueres.backendqualification.entities.ApplicationUser;
import net.diegoqueres.backendqualification.repositories.ApplicationUserRepository;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@PostMapping("/signup")
	public void signUp(@RequestBody ApplicationUser user) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		applicationUserRepository.save(user);
	}

}
