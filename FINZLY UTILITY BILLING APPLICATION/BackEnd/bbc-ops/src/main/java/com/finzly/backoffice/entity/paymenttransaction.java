package com.finzly.backoffice.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class paymenttransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionid;
	private Long bid;
	private Long cid;
	private Double paymentamt;
	private String paymentmethod;
	private Long referencenumber;
	private Date transactiondate;
	private String transactionstatus;
	private Long cardnumber;
	
	
	public Long getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(Long transactionid) {
		this.transactionid = transactionid;
	}
	public Long getBid() {
		return bid;
	}
	public void setBid(Long bid) {
		this.bid = bid;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Double getPaymentamt() {
		return paymentamt;
	}
	public void setPaymentamt(Double paymentamt) {
		this.paymentamt = paymentamt;
	}
	public String getPaymentmethod() {
		return paymentmethod;
	}
	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}
	public Long getReferencenumber() {
		return referencenumber;
	}
	public void setReferencenumber(Long referencenumber) {
		this.referencenumber = referencenumber;
	}
	public Date getTransactiondate() {
		return transactiondate;
	}
	public void setTransactiondate(Date transactiondate) {
		this.transactiondate = transactiondate;
	}
	public String getTransactionstatus() {
		return transactionstatus;
	}
	public void setTransactionstatus(String transactionstatus) {
		this.transactionstatus = transactionstatus;
	}
	public Long getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(Long cardnumber) {
		this.cardnumber = cardnumber;
	}
	
	

}
