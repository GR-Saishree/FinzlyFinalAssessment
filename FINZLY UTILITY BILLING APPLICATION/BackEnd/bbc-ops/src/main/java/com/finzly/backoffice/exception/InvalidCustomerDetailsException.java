package com.finzly.backoffice.exception;

public class InvalidCustomerDetailsException extends RuntimeException {

	public InvalidCustomerDetailsException() {
		super();
	}
	
	public InvalidCustomerDetailsException(String message) {
		super(message);
	}
}
