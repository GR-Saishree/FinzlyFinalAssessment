package com.finzly.backoffice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

	@Id
	private Integer employeeID;
	private String employeeName;
	private String employeeEmail;
	private String employeeMobile;
	private Integer employeeOTP;
	
	
	public Integer getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public String getEmployeeMobile() {
		return employeeMobile;
	}
	public void setEmployeeMobile(String employeeMobile) {
		this.employeeMobile = employeeMobile;
	}
	public Integer getEmployeeOTP() {
		return employeeOTP;
	}
	public void setEmployeeOTP(Integer employeeOTP) {
		this.employeeOTP = employeeOTP;
	}
}
