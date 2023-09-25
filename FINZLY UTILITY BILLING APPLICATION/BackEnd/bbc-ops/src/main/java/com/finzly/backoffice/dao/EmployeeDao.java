package com.finzly.backoffice.dao;

/*
 * @author Sai shree
 * 
 * */
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finzly.backoffice.entity.Employee;
import com.finzly.backoffice.exception.EmployeeIDNotExistsException;
import com.finzly.backoffice.exception.InvalidEmployeeOTPException;

@Repository
public class EmployeeDao {

	
	// Initialize a logger instance for EmployeeDao class using LoggerFactory
	private final Logger logger = LoggerFactory.getLogger(EmployeeDao.class);
	@Autowired
	private SessionFactory factory;

	// A method to store employee details in database
	public void signupEmployee(Employee employee) {
		Session session = factory.openSession();
		session.save(employee);
		logger.info("Employee details stored in database");
		session.beginTransaction().commit();
	}

	// A method checks whether employeeID exist as application is auto generating employee ID
	public boolean isEmployeeIDExists(Integer generatedEmployeeID) {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("employeeID", generatedEmployeeID));
	    if(criteria.list().isEmpty()) {
	    	return false;
	    }
	    else {
	    	return true;
	    }
	}

	// A method checks whether employeeOTP exist as application is auto generating employee OTP
	public boolean isEmployeeOTPExists(Integer generatedEmployeeOTP) {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("employeeOTP", generatedEmployeeOTP));
	    if(criteria.list().isEmpty()) {
	    	return false;
	    }
	    else {
	    	return true;
	    }
		
	}
	
	// A method checks if an employee with specified email exist
	public boolean isEmployeeEmailExists(String employeeEmail) {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("employeeEmail", employeeEmail));
	    if(criteria.list().isEmpty()) {
	    	return false;
	    }
	    else {
	    	return true;
	    }
	}
	
	
	// A method checks if an employee with specified mobile number exist
	public boolean isEmployeeMobileExists(String employeeMobile) {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("employeeMobile", employeeMobile));
	    if(criteria.list().isEmpty()) {
	    	return false;
	    }
	    else {
	    	return true;
	    }
	}

	// A method for employee login
	public String loginEmployee(Integer employeeID, Integer employeeOTP) {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("employeeID", employeeID));
		if(criteria.list().isEmpty()) {
			throw new EmployeeIDNotExistsException("Employee ID not exist. Please enter valid employee ID");
		}
		else {
			criteria.setProjection(Projections.projectionList().add(Projections.property("employeeOTP")));
			List<Integer> employeeotp = criteria.list();
			if(employeeOTP.equals(employeeotp.get(0))) {
				return "Welcome Employee "+ employeeID;
			}
			else {
				throw new InvalidEmployeeOTPException("Invalid Employee OTP");
			}
			
		}
	}
		
	

}
