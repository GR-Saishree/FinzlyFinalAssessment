package com.finzly.backoffice.dao;

/*
 * @author Sai shree
 * */
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finzly.backoffice.entity.CustomerDetails;
import com.finzly.backoffice.exception.CustomerAlreadyExistsException;

@Repository
public class CustomerDao {

	@Autowired
	SessionFactory factory;
	
	// Initialize a logger instance for CustomerDao class using LoggerFactory
	private final Logger logger = LoggerFactory.getLogger(CustomerDao.class);

	// A method that stores customer details in database via input from CSV file
	public String addBulkCustomerDetails(List<CustomerDetails> customerDetailsList) {
		Session session = factory.openSession();
		Transaction transaction = null;
		boolean duplicateCustomerDetected = false;

		try {
			transaction = session.beginTransaction();
			for (CustomerDetails customerDetails : customerDetailsList) {
				if (!checkCustomer(customerDetails.getcid())) {
					session.save(customerDetails);
					logger.info("Customer details stored in database via a input from CSV");
				} else {
					duplicateCustomerDetected = true;
				}

			}
			transaction.commit();
			if (duplicateCustomerDetected) {
				return "Some customers already exist in the database. Bulk customer details added, but duplicates were skipped";
			} else {
				return "Bulk customer details added successfully";
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return "Error adding bulk customer details";
		}

	}

	// A method stores customer details to database , input from forms
	public String manualCustomerEntry(CustomerDetails customerDetails) {
		Session session = factory.openSession();
		session.save(customerDetails);
		logger.info("Customer details stored in database via input from forms");
		session.beginTransaction().commit();
		return "Customer Details added successfully";

	}

	// A method checks whether customer exists in table based on customer ID
	public boolean checkCustomer(Long customerID) {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(CustomerDetails.class);
		criteria.add(Restrictions.eq("cid", customerID));
		return !criteria.list().isEmpty();

	}

}
