package com.finzly.backoffice.exception;

public class CustomerNotExistException extends RuntimeException{

	public CustomerNotExistException() {
		super();
	}
	
	public CustomerNotExistException(String message) {
		super(message);
	}
}
