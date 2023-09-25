package com.finzly.backoffice.exception;

public class InvalidEmployeeMobileException extends RuntimeException{

	public InvalidEmployeeMobileException() {
		super();
	}
	
	public InvalidEmployeeMobileException(String message) {
		super(message);
	}
}
