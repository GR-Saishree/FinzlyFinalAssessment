package com.finzly.backoffice.exception;

public class EmployeeEmailAlreadyExistsException  extends RuntimeException{

	public EmployeeEmailAlreadyExistsException() {
		super();
	}
	
	public EmployeeEmailAlreadyExistsException(String message) {
		super(message);
	}
}
