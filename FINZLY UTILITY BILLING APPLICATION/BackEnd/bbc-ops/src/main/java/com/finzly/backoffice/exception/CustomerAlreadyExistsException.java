package com.finzly.backoffice.exception;

public class CustomerAlreadyExistsException extends RuntimeException{

	public CustomerAlreadyExistsException() {
		super();
	}
	
	public CustomerAlreadyExistsException(String message) {
		super(message);
	}
}
