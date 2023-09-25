package com.finzly.backoffice.exception;

public class PaymentUpdateException extends RuntimeException{

	public PaymentUpdateException() {
		super();
	}
	
	public PaymentUpdateException(String message) {
		super(message);
	}
}
