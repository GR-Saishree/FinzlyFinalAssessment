package com.finzly.backoffice.exception;

public class InvalidEmployeeDetailsException extends RuntimeException{

	
	public InvalidEmployeeDetailsException() {
		super();
	}
	
	public InvalidEmployeeDetailsException(String message) {
		super(message);
	}
}
