package com.finzly.backoffice.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(InvalidEmployeeNameException.class)
	public String invalidEmployeeNameException(InvalidEmployeeNameException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(InvalidEmployeeEmailException.class)
	public String invalidEmployeeEmailException(InvalidEmployeeEmailException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(InvalidEmployeeMobileException.class)
	public String invalidEmployeeMobileException(InvalidEmployeeMobileException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(EmployeeEmailMobileAlreadyExistsException.class)
	public String employeeEmailMobileAlreadyExistsException(EmployeeEmailMobileAlreadyExistsException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(EmployeeEmailAlreadyExistsException.class)
	public String employeeEmailAlreadyExistsException(EmployeeEmailAlreadyExistsException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(EmployeeMobileAlreadyExistsException.class)
	public String employeeMobileAlreadyExistsException(EmployeeMobileAlreadyExistsException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(InvalidEmployeeDetailsException.class)
	public String invalidEmployeeDetailsException(InvalidEmployeeDetailsException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(InvalidCustomerDetailsException.class)
	public String invalidCustomerDetailsException(InvalidCustomerDetailsException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(InvalidBillDetailsException.class)
	public String invalidBillDetailsException(InvalidBillDetailsException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(EmployeeIDNotExistsException.class)
	public String employeeIDNotExistsException(EmployeeIDNotExistsException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(InvalidEmployeeOTPException.class)
	public String invalidEmployeeOTPException(InvalidEmployeeOTPException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public String customerAlreadyExistsException(CustomerAlreadyExistsException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(NoTransactionFoundException.class)
	public String noTransactionFoundException(NoTransactionFoundException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(PaymentUpdateException.class)
	public String paymentUpdateException(PaymentUpdateException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(CustomerNotExistException.class)
	public String customerNotExistException(CustomerNotExistException exception) {
		return exception.getMessage();
	}
	
}
