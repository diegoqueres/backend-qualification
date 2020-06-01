package net.diegoqueres.backendqualification.services.exceptions;

/**
 * Exceção decorrente de operações que dependem da existência da entidade para
 * rodar. Mas não foram localizadas ocorrências para prosseguir.
 * 
 * @author diego
 *
 */
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 5240155570957303142L;

	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}

}
