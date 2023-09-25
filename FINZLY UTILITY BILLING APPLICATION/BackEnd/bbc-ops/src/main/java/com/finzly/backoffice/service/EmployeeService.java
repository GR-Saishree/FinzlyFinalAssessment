package com.finzly.backoffice.service;


/*
 * @author Sai shree
 * 
 * */
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finzly.backoffice.dao.EmployeeDao;
import com.finzly.backoffice.entity.Employee;
import com.finzly.backoffice.exception.EmployeeEmailAlreadyExistsException;
import com.finzly.backoffice.exception.EmployeeEmailMobileAlreadyExistsException;
import com.finzly.backoffice.exception.EmployeeMobileAlreadyExistsException;
import com.finzly.backoffice.exception.InvalidEmployeeDetailsException;
import com.finzly.backoffice.exception.InvalidEmployeeEmailException;
import com.finzly.backoffice.exception.InvalidEmployeeMobileException;
import com.finzly.backoffice.exception.InvalidEmployeeNameException;

@Service
public class EmployeeService {

	
	@Autowired EmployeeDao employeeDao;
	
	// A method which sends employee details to DAO layer
	public Map<String, Integer> signupEmployee(Employee employee) {
		Map<String, Integer> registrationResponse = new HashMap<>();
		
		if(isValidEmployee(employee)) {
			
			
			Integer generatedEmployeeID = generateRandomEmployeeID();
			employee.setEmployeeID(generatedEmployeeID);
			
			
			Integer generatedEmployeeOTP = generateRandomEmployeeOTP();
			employee.setEmployeeOTP(generatedEmployeeOTP);
			
			if(employeeDao.isEmployeeEmailExists(employee.getEmployeeEmail()) && employeeDao.isEmployeeMobileExists(employee.getEmployeeMobile())){
				throw new EmployeeEmailMobileAlreadyExistsException("Employee with given email ID and mobile number already exists");
			}
			if(employeeDao.isEmployeeEmailExists(employee.getEmployeeEmail())){
				throw new EmployeeEmailAlreadyExistsException("Employee with given email ID already exists");
			}
			if(employeeDao.isEmployeeMobileExists(employee.getEmployeeMobile())) {
				throw new EmployeeMobileAlreadyExistsException("Employee with given mobile number already exists");
			}
			if(!employeeDao.isEmployeeIDExists(generatedEmployeeID) && !employeeDao.isEmployeeOTPExists(generatedEmployeeOTP)) {
				employeeDao.signupEmployee(employee);
				
				registrationResponse.put("employeeID", generatedEmployeeID);
				registrationResponse.put("employeeOTP", generatedEmployeeOTP);
				
				return registrationResponse;
				
			}
			
		}
			registrationResponse.put("error", -1);
			return registrationResponse;
	 }
	
	
	// A method to generate random employee ID
	private Integer generateRandomEmployeeID() {
		Random randomID = new Random();
		return randomID.nextInt(9000)+1000;
	}

	// A method to generate random employee OTP
	private Integer generateRandomEmployeeOTP() {
		Random randomOTP = new Random();
		return randomOTP.nextInt(900000)+100000;
	}
	
	// A method to check whether employee details are valid
	private boolean isValidEmployee(Employee employee) {
		if(isValidName(employee.getEmployeeName()) && isValidEmail(employee.getEmployeeEmail()) && isValidMobile(employee.getEmployeeMobile())) {
			return true;
		}
		else if(!isValidName(employee.getEmployeeName()) && !isValidEmail(employee.getEmployeeEmail()) && !isValidMobile(employee.getEmployeeMobile())) {
			throw new InvalidEmployeeDetailsException("Invalid Employee Details. Please enter valid details");
		}
		else if(!isValidName(employee.getEmployeeName())){
			throw new InvalidEmployeeNameException("Invalid Name. Please enter a valid name");
		}
		else if(!isValidEmail(employee.getEmployeeEmail())){
			throw new InvalidEmployeeEmailException("Invalid Email ID. Please enter a valid mail ID");
		}
		else if(!isValidMobile(employee.getEmployeeMobile())){
			throw new InvalidEmployeeMobileException("Invalid Mobile Number. Please enter a valid mobile number");
		}
		else {
			return false;
		}
	}
	
	// A method to validate name
	private boolean isValidName(String employeeName) {
		if(employeeName.matches("^[a-zA-Z. ]+$") && !employeeName.isEmpty() && !employeeName.equals(" ")) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	// A method to validate email
	private boolean isValidEmail(String employeeEmail) {
		if(employeeEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$") && !employeeEmail.isEmpty() && !employeeEmail.equals(" ")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// A method to validate mobile number
	private boolean isValidMobile(String employeeMobile) {
		if(employeeMobile.matches("^(\\+\\d{1,3}[- ]?)?\\d{10}$") && !employeeMobile.isEmpty() && !employeeMobile.equals(" ")) {
			return true;
		}
		else {
			return false;
		}
	}


	// A method which sends login details to DAO layer
	public String loginEmployee(Integer employeeID, Integer employeeOTP) {
		return employeeDao.loginEmployee(employeeID,employeeOTP);
	}
	
	
}
