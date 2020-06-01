package net.diegoqueres.backendqualification.resources.exceptions;

import java.util.List;

import org.springframework.validation.ObjectError;

/**
 * Representa erros de validação capturados quando a API recebe o objeto DTO.
 * 
 * @author Diego Queres
 *
 */
public class ValidationException extends RuntimeException {
	private static final long serialVersionUID = 6317560488887107500L;

	private List<ObjectError> errorList;

	public ValidationException(List<ObjectError> errorList) {
		super();
		this.errorList = errorList;
	}

	public List<ObjectError> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<ObjectError> errorList) {
		this.errorList = errorList;
	}

}