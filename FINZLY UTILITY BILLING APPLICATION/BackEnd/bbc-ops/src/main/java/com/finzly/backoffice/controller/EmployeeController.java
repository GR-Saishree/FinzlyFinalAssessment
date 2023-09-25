package com.finzly.backoffice.controller;


/*
 * 
 * @author Sai shree
 * */
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finzly.backoffice.entity.Employee;
import com.finzly.backoffice.service.EmployeeService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class EmployeeController {

	
	@Autowired
	private EmployeeService employeeService;
	
	// API to register an employee
	@PostMapping("signupEmployee")
	public ResponseEntity<?> signupEmployee(@RequestBody Employee employee){
		Map<String, Integer> registrationResponse = employeeService.signupEmployee(employee);
		if(registrationResponse.containsKey("error")) {
			return ResponseEntity.badRequest().body("Registration failed");
		}
		return ResponseEntity.ok(registrationResponse);
	}
	
	// API to login an employee
	@GetMapping("loginEmployee")
	public String loginEmployee(@RequestParam(name="employeeID")Integer employeeID, @RequestParam(name="employeeOTP") Integer employeeOTP) {
		return employeeService.loginEmployee(employeeID,employeeOTP);
	}
	
	
	
	
	
	
	
}
