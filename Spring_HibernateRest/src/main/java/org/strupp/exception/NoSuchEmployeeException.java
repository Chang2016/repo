package org.strupp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Employee Not Found") //404
public class NoSuchEmployeeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4978931192551134616L;
	
	public NoSuchEmployeeException(long id) {
		super("EmployeeNotFoundException with id=" + id);
	}
}
