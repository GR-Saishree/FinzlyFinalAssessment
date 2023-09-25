package com.finzly.backoffice.exception;

public class InvalidBillDetailsException extends RuntimeException {

	public InvalidBillDetailsException() {
		super();
	}
	public InvalidBillDetailsException(String message) {
		super(message);
	}
}
