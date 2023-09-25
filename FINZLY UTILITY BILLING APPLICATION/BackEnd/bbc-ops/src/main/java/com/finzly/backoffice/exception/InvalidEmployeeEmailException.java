package com.finzly.backoffice.exception;

public class InvalidEmployeeEmailException extends RuntimeException{

	public InvalidEmployeeEmailException() {
		super();
	}
	
	public InvalidEmployeeEmailException(String message) {
		super(message);
	}
}
