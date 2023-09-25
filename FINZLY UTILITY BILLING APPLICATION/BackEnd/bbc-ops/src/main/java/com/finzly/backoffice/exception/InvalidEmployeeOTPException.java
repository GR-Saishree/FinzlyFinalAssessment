package com.finzly.backoffice.exception;

public class InvalidEmployeeOTPException extends RuntimeException {

	public InvalidEmployeeOTPException() {
		super();
	}
	
	public InvalidEmployeeOTPException(String message) {
		super(message);
	}
}
