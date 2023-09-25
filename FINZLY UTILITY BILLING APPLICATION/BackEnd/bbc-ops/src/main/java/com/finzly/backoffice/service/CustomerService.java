package com.finzly.backoffice.service;

/*
 * @author Sai shree
 * 
 * */
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finzly.backoffice.dao.CustomerDao;
import com.finzly.backoffice.entity.CustomerDetails;
import com.finzly.backoffice.exception.CustomerAlreadyExistsException;
import com.finzly.backoffice.exception.InvalidCustomerDetailsException;

@Service
public class CustomerService {

	@Autowired
	CustomerDao customerDao;
	
	// A method sends customer details to the DAO layer
	public String manualCustomerEntry(CustomerDetails customerDetails) {
		if(!isValidCustomer(customerDetails)) {
			return "Invalid customer";
		}
		if(isCustomerAlreadyExists(customerDetails.getcid())) {
			throw new CustomerAlreadyExistsException("Customer Already Exist");
				
		}
		else {
			return customerDao.manualCustomerEntry(customerDetails);
		}
		
		
	}
	
	// A method sends customer ID to the DAO layer by which it determines whether customer already exist
	private boolean isCustomerAlreadyExists(Long customerID) {
		return customerDao.checkCustomer(customerID);
	}

	
	// A method which checks validity of customer details
	private boolean isValidCustomer(CustomerDetails customerDetails) {
		if(isValidName(customerDetails.getcustomername()) && isValidEmail(customerDetails.getemailid()) && isValidMobile(customerDetails.getmobileno())
				&& isValidHouseNumber(customerDetails.gethouseno()) && isValidPincode(customerDetails.getPincode())){
			return true;
		}
		else if(!isValidName(customerDetails.getcustomername()) && !isValidEmail(customerDetails.getemailid()) && !isValidMobile(customerDetails.getmobileno())
				&& !isValidHouseNumber(customerDetails.gethouseno()) && !isValidPincode(customerDetails.getPincode())) {
			throw new InvalidCustomerDetailsException("Invalid input. Please enter a valid input");
		}
		else {
			return false;
		}
			
			
		
	}
	
	// A method which checks whether name is valid
	private boolean isValidName(String customerName) {
		if(customerName.matches("^[a-zA-Z. ]+$") && !customerName.isEmpty() && !customerName.equals(" ")) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	// A method which checks whether email is valid
	private boolean isValidEmail(String customerEmail) {
		if(customerEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$") && !customerEmail.isEmpty() && !customerEmail.equals(" ")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	// A method which checks whether mobile number is valid
	private boolean isValidMobile(String customerMobile) {
		if(customerMobile.matches("^(\\+\\d{1,3}[- ]?)?\\d{10}$") && !customerMobile.isEmpty() && !customerMobile.equals(" ")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	// A method which checks whether house number is valid
	private boolean isValidHouseNumber(Integer customerHouseNumber) {
		Integer minVal = 1;
		Integer maxValue= 10000;
		
		return (customerHouseNumber >= minVal && customerHouseNumber <=maxValue);
	}
	
	
	// A method which checks whether pincode is valid
	private boolean isValidPincode(Long pincode) {
		Long minVal = 100000L;
		Long maxValue= 999999L;
		
		return (pincode >= minVal && pincode <=maxValue);
	}

	// A method which sends list of customer details to DAO layer
	public String addBulkCustomerDetails(List<CustomerDetails> customerDetailsList) {
		return customerDao.addBulkCustomerDetails(customerDetailsList);
		
	}

	
	

}
