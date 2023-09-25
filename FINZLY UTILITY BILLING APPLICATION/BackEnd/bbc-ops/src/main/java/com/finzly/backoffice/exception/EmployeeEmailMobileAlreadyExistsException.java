package com.finzly.backoffice.exception;

public class EmployeeEmailMobileAlreadyExistsException extends RuntimeException {

	
	public EmployeeEmailMobileAlreadyExistsException() {
		super();
	}
	
	public EmployeeEmailMobileAlreadyExistsException(String message) {
		super(message);
	}
}
