package com.finzly.backoffice.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long invoiceID;
	
	private Long billingID;
	
	private Long customerID;
	
	private String customerName;
	
	private Date invoiceDate;
	
	private Date billDueDate;
	
	private Double totalAmount;
	
	private Double earlyPaymentDiscount;
	
	private Double onlinePaymentDiscount;
	
	private Double totalDiscountAmount;
	

	public Long getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(Long invoiceID) {
		this.invoiceID = invoiceID;
	}

	

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Date getBillDueDate() {
		return billDueDate;
	}

	public void setBillDueDate(Date billDueDate) {
		this.billDueDate = billDueDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getEarlyPaymentDiscount() {
		return earlyPaymentDiscount;
	}

	public void setEarlyPaymentDiscount(Double earlyPaymentDiscount) {
		this.earlyPaymentDiscount = earlyPaymentDiscount;
	}

	public Double getOnlinePaymentDiscount() {
		return onlinePaymentDiscount;
	}

	public void setOnlinePaymentDiscount(Double onlinePaymentDiscount) {
		this.onlinePaymentDiscount = onlinePaymentDiscount;
	}

	public Double getTotalDiscountAmount() {
		return totalDiscountAmount;
	}

	public void setTotalDiscountAmount(Double totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}

	public Long getBillingID() {
		return billingID;
	}

	public void setBillingID(Long billingID) {
		this.billingID = billingID;
	}

	

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	
}
