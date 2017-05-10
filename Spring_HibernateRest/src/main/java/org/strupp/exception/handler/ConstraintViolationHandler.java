package org.strupp.exception.handler;

import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.strupp.exception.jsonresponse.ValidationErrorDTO;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ConstraintViolationHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ ConstraintViolationException.class })
	protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
		ConstraintViolationException cve = (ConstraintViolationException) e;
		Set<ConstraintViolation<?>> violations = cve.getConstraintViolations();
		ValidationErrorDTO error = new ValidationErrorDTO();
        violations.forEach(v -> error.addFieldError(v.getPropertyPath().toString(), v.getMessage()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
	}
}
