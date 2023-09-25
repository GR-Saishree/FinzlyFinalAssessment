package com.finzly.backoffice.dao;

/*
 * @author Sai shree
 * 
 * */
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.DateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finzly.backoffice.controller.InvoiceController;
import com.finzly.backoffice.entity.BillingDetails;
import com.finzly.backoffice.entity.CustomerDetails;
import com.finzly.backoffice.exception.CustomerNotExistException;



@Repository
public class BillingDetailsDao {

	
	// Initialize a logger instance for BillingDetailsDao class using LoggerFactory
	private final Logger logger = LoggerFactory.getLogger(BillingDetailsDao.class);
	@Autowired
	SessionFactory factory;
	
	@Autowired
	InvoiceController invoiceController;
	
	// A method to store the values in database
	public String manualBillEntry(BillingDetails billingDetails) {
		Session session = factory.openSession();
		if(checkCustomerExist(billingDetails.getCustomerID())) {
			session.save(billingDetails);
		}
		else {
			throw new CustomerNotExistException("Customer Not Exist, Enter Valid Customer ID");
		}
		invoiceController.generateInvoice(billingDetails.getBillingID());
		session.beginTransaction().commit();
		logger.info("Billing entry successfull");
		return "Successfuly registered with Billing ID: "+billingDetails.getBillingID();
		
	}
	
	// A method which checks whether same start date is present for a customer to avoid invalid data
	public boolean checkSameStartDate(BillingDetails billingDetails){
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(BillingDetails.class);
		Date startDate = billingDetails.getStartDate();
		Date startOfDay = getStartOfDay(startDate);
        Date endOfDay = getEndOfDay(startDate);
        criteria.add(Restrictions.eq("customerID", billingDetails.getCustomerID()));
        criteria.add(Restrictions.between("startDate", startOfDay, endOfDay));

        
		List<?> result= criteria.list();
		return !result.isEmpty();
	}
	
	private Date getStartOfDay(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    return calendar.getTime();
	}

	// Helper function to get the end of the day
	private Date getEndOfDay(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);
	    calendar.set(Calendar.MILLISECOND, 999);
	    return calendar.getTime();
	}
	
	
	// A method that stores billing details in database which are uploaded via CSV
	public void addBulkBillingDetails(List<BillingDetails> billingDetailsList) {
		Session session = factory.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			for(BillingDetails billingDetails: billingDetailsList) {
				boolean isStartDateSame = checkSameStartDate(billingDetails);
				if(!isStartDateSame) {
					session.save(billingDetails);
					logger.info("Bulk Billing entry successfull");
				}
				
			}
			transaction.commit();
		}
		catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
	}

	// A method that checks whether a particular customer exist
	private boolean checkCustomerExist(Long customerID) {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(CustomerDetails.class);
		criteria.add(Restrictions.eq("cid", customerID));
		return !criteria.list().isEmpty();
	}
}
