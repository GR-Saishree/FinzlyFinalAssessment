package com.finzly.backoffice.controller;

/*
 * 
 * @author Sai shree
 * 
 * */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finzly.backoffice.entity.paymenttransaction;
import com.finzly.backoffice.service.PaymentTransactionService;


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class PaymentTransactionController {

	
	@Autowired
	PaymentTransactionService paymentTransactionservice;
	
	// API to get transaction history by specifying customer ID(cid)
	@GetMapping("getTransactionHistory/{cid}")
	public List<paymenttransaction> getTransactionHistory(@PathVariable Long cid){
		return paymentTransactionservice.getTransactionHistory(cid);
	}
	
	// API to mark payment status by getting transaction ID
	@PutMapping("markPaymentMadeInCash/{transactionid}")
	public String markPaymentMadeInCash(@PathVariable Long transactionid,@RequestParam String status) {
		return paymentTransactionservice.markPaymentMadeInCash(transactionid,status);
	}
}
