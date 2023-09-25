package com.finzly.backoffice.exception;

public class EmployeeIDNotExistsException extends RuntimeException{

	public EmployeeIDNotExistsException() {
		super();
	}
	
	public EmployeeIDNotExistsException(String message) {
		super(message);
	}
}
