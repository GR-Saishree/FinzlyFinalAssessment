package com.finzly.backoffice.exception;

public class InvalidEmployeeNameException extends RuntimeException {

	public InvalidEmployeeNameException() {
		super();
	}
	
	public InvalidEmployeeNameException(String message) {
		super(message);
	}
}
