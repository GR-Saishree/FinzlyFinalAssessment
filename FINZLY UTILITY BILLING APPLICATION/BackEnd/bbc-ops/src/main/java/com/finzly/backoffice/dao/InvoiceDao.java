package com.finzly.backoffice.dao;

/*
 * @author Sai shree
 * 
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

import com.finzly.backoffice.entity.BillingDetails;
import com.finzly.backoffice.entity.CustomerDetails;
import com.finzly.backoffice.entity.Invoice;

@Repository
public class InvoiceDao {

	// Initialize a logger instance for InvoiceDao class using LoggerFactory
	private final Logger logger = LoggerFactory.getLogger(InvoiceDao.class);
	
	@Autowired
	SessionFactory factory;

	// A method finds billing details by the specified ID
	public List<BillingDetails> findBillingDetailsByID(Long billingID) {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(BillingDetails.class);
		criteria.add(Restrictions.eq("billingID", billingID));
		return criteria.list();
		
	}

	// A method stores invoice details in database
	public String addInvoice(Invoice invoice) {
		Session session = factory.openSession();
		session.save(invoice);
		logger.info("Invoice details stored in database");
		session.beginTransaction().commit();
		return "Invoice details added successfully";
		
	}

	// A method fetches customer name by specified ID
	public String getCustomerNameByID(Long customerID) {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(CustomerDetails.class);
		criteria.add(Restrictions.eq("cid", customerID));
		criteria.setProjection(Projections.property("customername"));
		return (String) criteria.uniqueResult();
	}

	// A method fetches generated invoice by specified billing ID
	public List<Invoice> getGeneratedInvoice(Long billingID) {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Invoice.class);
		criteria.add(Restrictions.eq("billingID", billingID));
		return criteria.list();
	}

	
}
