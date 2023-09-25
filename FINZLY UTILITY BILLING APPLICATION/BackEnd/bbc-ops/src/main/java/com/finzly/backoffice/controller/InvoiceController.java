package com.finzly.backoffice.controller;

/*
 * @author Sai shree
 * 
 * */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finzly.backoffice.entity.Invoice;
import com.finzly.backoffice.service.InvoiceService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;
	
	// API to generate invoice with respect to the billing ID
	@PostMapping("generateInvoice/{billingID}")
	public String generateInvoice(@PathVariable Long billingID) {
		return invoiceService.generateInvoice(billingID);
	}
	
	// API to get generated invoice by specifying billing ID
	@GetMapping("getGeneratedInvoice/{billingID}")
	public List<Invoice> getGeneratedInvoice(@PathVariable Long billingID){
		return invoiceService.getGeneratedInvoice(billingID);
	}
	
	
}
