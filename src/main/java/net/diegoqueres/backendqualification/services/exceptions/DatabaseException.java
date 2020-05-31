package net.diegoqueres.backendqualification.services.exceptions;

/**
 * Exceções decorrentes de operações no banco de dados.
 * 
 * @author diego
 *
 */
public class DatabaseException extends RuntimeException {
	private static final long serialVersionUID = 2383228322629012347L;

	public DatabaseException(String msg) {
		super(msg);
	}

}
