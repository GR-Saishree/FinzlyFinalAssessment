package com.finzly.backoffice.dao;

/*
 * 
 * @author Sai shree
 * */
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finzly.backoffice.entity.paymenttransaction;

@Repository
public class PaymentTransactionDao {

	// Initialize a logger instance for PaymentTransactionDao class using LoggerFactory
	private final Logger logger = LoggerFactory.getLogger(PaymentTransactionDao.class);
	
	@Autowired
	SessionFactory factory;
	
	// A method fetches Transaction History by specified customer ID
	public List<paymenttransaction> getTransactionHistory(Long cid) {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(paymenttransaction.class);
		criteria.add(Restrictions.eq("cid", cid));
		return criteria.list();
	}
	
	// A method fetches Payment details by specified Transaction Id
	public List<paymenttransaction> getPaymentDetailsByTransactionId(Long transactionid) {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(paymenttransaction.class);
		criteria.add(Restrictions.eq("transactionid", transactionid));
		return criteria.list();
		
	}
	
	// A method updates payment transaction by specified transaction ID
	public void updatePaymentTransaction(Long transactionid,String status) {
		Session session = factory.openSession();
		paymenttransaction payTransaction = session.get(paymenttransaction.class, transactionid);
		payTransaction.setTransactionstatus(status);
		session.update(payTransaction);
		logger.info("Updated Payment Transaction status");
		session.beginTransaction().commit();
		
	}

}
