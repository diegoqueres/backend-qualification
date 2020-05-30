package net.diegoqueres.backendqualification;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDocker {

	@GetMapping("/message")
	public String getMessage() {
		return "Teste do Docker bem sucedido!";
	}
		
}
