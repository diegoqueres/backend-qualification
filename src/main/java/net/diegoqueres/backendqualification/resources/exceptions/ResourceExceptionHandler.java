package net.diegoqueres.backendqualification.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import net.diegoqueres.backendqualification.services.exceptions.DatabaseException;
import net.diegoqueres.backendqualification.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<StandardError> validation(ValidationException e, HttpServletRequest request) {
		String error = "Validation exception";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		var msg = new StringBuilder();
		e.getErrorList().forEach(err -> msg.append(err.getDefaultMessage() + " "));
		StandardError err = new StandardError(Instant.now(), status.value(), error, msg.toString(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
