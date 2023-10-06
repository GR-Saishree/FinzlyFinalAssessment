package com.finzly.backoffice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finzly.backoffice.dao.BillingDetailsDao;
import com.finzly.backoffice.entity.BillingDetails;
import com.finzly.backoffice.exception.InvalidBillDetailsException;

@Service
public class BillingDetailsService {

	
	@Autowired
	BillingDetailsDao billingDetailsDao;
	
	// A method sends billing details to DAO layer
	public String manualBillEntry(BillingDetails billingDetails) {

		Date startDate = billingDetails.getStartDate();
		Date endDate = billingDetails.getEndDate();
		Date billDueDate = billingDetails.getBillDueDate();

		// Check if Start date is not after end date
		if(startDate.after(endDate)){
			return "Start date cannot be after end date";
		}

		// Check if End date is before start date
		if(endDate.before(startDate)){
			return "End date cannot be before start date";
		}

		// Check if Bill due date is before start date
		if(billDueDate.before(startDate)){
			return "Bill due date cannot be before the start date";
		}

		// Check if Bill due date is after end date
		if(!billDueDate.after(endDate)){
			return "Bill due date must be after end date";
		}
		
		if(validateUnitConsumption(billingDetails.getUnitConsumed()) && (!billingDetailsDao.checkSameStartDate(billingDetails)) ) {
			return billingDetailsDao.manualBillEntry(billingDetails);
		}
		else {
			throw new InvalidBillDetailsException("Invalid Bill Details");
		}
		
	}
	
	
	// A method to validate Unit consumption
	private boolean validateUnitConsumption(double unitConsumption) {
		return unitConsumption>=0;
	}

	// A method that sends list of billing details to DAO layer
	public void addBulkBillingDetails(List<BillingDetails> billingDetailsList) {

		for(BillingDetails billingDetails : billingDetailsList){
			Date startDate = billingDetails.getStartDate();
			Date endDate = billingDetails.getEndDate();
			Date billDueDate = billingDetails.getBillDueDate();

			// Check if Start date is not after end date
			if(startDate.after(endDate)){
				return "Start date cannot be after end date";
			}	

			// Check if End date is before start date
			if(endDate.before(startDate)){
				return "End date cannot be before start date";
			}

			// Check if Bill due date is before start date
			if(billDueDate.before(startDate)){
				return "Bill due date cannot be before the start date";
			}

			// Check if Bill due date is after end date
			if(!billDueDate.after(endDate)){
				return "Bill due date must be after end date";
			}
		}
		billingDetailsDao.addBulkBillingDetails(billingDetailsList);
		
	}


}
