package com.finzly.backoffice.entity;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class CustomerDetails {

	
	@Id
	private Long cid;
	private String customername;
	private String emailid;
	private String mobileno;
	private Integer houseno;
	private String address;
	private Long pincode;
	private String state;
	private String country;
	
	public Integer gethouseno() {
		return houseno;
	}
	public void sethouseno(Integer houseno) {
		this.houseno = houseno;
	}
	public String getaddress() {
		return address;
	}
	public void setaddress(String address) {
		this.address = address;
	}
	public Long getPincode() {
		return pincode;
	}
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public Long getcid() {
		return cid;
	}
	public void setcid(Long cid) {
		this.cid = cid;
	}
	public String getcustomername() {
		return customername;
	}
	public void setcustomername(String customername) {
		this.customername = customername;
	}
	public String getemailid() {
		return emailid;
	}
	public void setemailid(String emailid) {
		this.emailid = emailid;
	}
	public String getmobileno() {
		return mobileno;
	}
	public void setmobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	
		
		
	
}
