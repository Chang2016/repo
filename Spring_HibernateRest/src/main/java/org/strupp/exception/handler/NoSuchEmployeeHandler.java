package org.strupp.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.strupp.exception.NoSuchEmployeeException;
import org.strupp.exception.jsonresponse.NoSuchEmployeeResponse;

@ControllerAdvice
public class NoSuchEmployeeHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ NoSuchEmployeeException.class })
	protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
		NoSuchEmployeeException mje = (NoSuchEmployeeException) e;
		
		NoSuchEmployeeResponse error = new NoSuchEmployeeResponse("InvalidRequest", mje.getMessage());
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
	}
}
