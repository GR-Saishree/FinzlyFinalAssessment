package com.finzly.backoffice.service;

/*
 * 
 * @author Sai shree*/
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finzly.backoffice.dao.PaymentTransactionDao;
import com.finzly.backoffice.entity.paymenttransaction;
import com.finzly.backoffice.exception.NoTransactionFoundException;
import com.finzly.backoffice.exception.PaymentUpdateException;

@Service
public class PaymentTransactionService {

	
	@Autowired 
	PaymentTransactionDao paymentTransactionDao;
	
	// A method which sends customer ID to DAO Layer
	public List<paymenttransaction> getTransactionHistory(Long cid) {
		return paymentTransactionDao.getTransactionHistory(cid);
	}

	// A method which sends transaction ID and status to DAO layer
	public String markPaymentMadeInCash(Long transactionid, String status) {
		List<paymenttransaction> transactions = paymentTransactionDao.getPaymentDetailsByTransactionId(transactionid);
		
		if(transactions.isEmpty()) {
			throw new NoTransactionFoundException("No Transaction is Found for specified Transaction ID");
		}
		
		boolean updated = false;
		for(paymenttransaction transaction : transactions) {
			if(transaction.getPaymentmethod().equalsIgnoreCase("cash")) {
				transaction.setTransactionstatus(status);
				try {
					paymentTransactionDao.updatePaymentTransaction(transactionid,status);
					updated=true;
				}catch(Exception e) {
					throw new PaymentUpdateException("Unable to update payment transaction details");
				}
				
			}
		}
		
		if(updated) {
			return "Payment status updated to success";
		}
		else {
			return "No cash payments found for the specified billing ID";
		}
	}

}
