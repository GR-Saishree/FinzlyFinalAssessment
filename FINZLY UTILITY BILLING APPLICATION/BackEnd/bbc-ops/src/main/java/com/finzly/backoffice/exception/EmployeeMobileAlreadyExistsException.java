package com.finzly.backoffice.exception;

public class EmployeeMobileAlreadyExistsException extends RuntimeException {

	
	public EmployeeMobileAlreadyExistsException() {
		super();
	}
	
	public EmployeeMobileAlreadyExistsException(String message) {
		super(message);
	}
}
