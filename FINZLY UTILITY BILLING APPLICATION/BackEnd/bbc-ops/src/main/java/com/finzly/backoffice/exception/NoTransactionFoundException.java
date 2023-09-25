package com.finzly.backoffice.exception;

public class NoTransactionFoundException extends RuntimeException{

	public NoTransactionFoundException() {
		super();
	}
	
	public NoTransactionFoundException(String message) {
		super(message);
	}
}
