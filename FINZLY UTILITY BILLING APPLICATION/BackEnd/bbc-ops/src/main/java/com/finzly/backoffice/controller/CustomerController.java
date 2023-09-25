package com.finzly.backoffice.controller;

/*
 * @author Sai shree
 * */
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.common.record.Record;
import com.finzly.backoffice.entity.BillingDetails;
import com.finzly.backoffice.entity.CustomerDetails;
import com.finzly.backoffice.exception.CustomerAlreadyExistsException;
import com.finzly.backoffice.service.BillingDetailsService;
import com.finzly.backoffice.service.CustomerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CustomerController {

	String result = "";

	// Initialize a logger instance for Customer Controller class using
	// LoggerFactory
	private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService customerService;

	@Autowired
	BillingDetailsService billDetailsService;

	@Autowired
	InvoiceController invoiceController;

	// API to do customer entry with user provided values in form
	@PostMapping("manualCustomerEntry")
	public String manualCustomerEntry(@RequestBody CustomerDetails customerDetails) {
		return customerService.manualCustomerEntry(customerDetails);
		
	}

	// API to do bulk upload using a CSV file
	@PostMapping("bulkUpload")
	public String uploadData(@RequestParam("file") MultipartFile file) throws Exception {

		List<CustomerDetails> customerDetailsList = new ArrayList<>();
		List<BillingDetails> billingDetailsList = new ArrayList<>();
		Map<String, String> validationErrors = new HashMap<>();

		InputStream inputStream = file.getInputStream();
		CsvParserSettings setting = new CsvParserSettings();
		setting.setHeaderExtractionEnabled(true);
		CsvParser parser = new CsvParser(setting);
		List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
		parseAllRecords.forEach(record -> {

			// To make validations simpler we are using String
			String cidStr = record.getString("cid");
			String customerName = record.getString("customername");
			String emailId = record.getString("emailid");
			String mobileNo = record.getString("mobileno");
			String houseNoStr = record.getString("houseno");
			String address = record.getString("address");
			String pincodeStr = record.getString("pincode");
			String state = record.getString("state");
			String country = record.getString("country");
			String unitConsumedStr = record.getString("unit_consumed");

			String cidValidation = validateNumber(cidStr);
			if (cidValidation != null) {
				validationErrors.put("cid", cidStr);
			}

			if (!isValidCustomerName(customerName) && customerName.isEmpty()) {
				validationErrors.put("customername", "Invalid or empty");
			}

			if (!isValidEmail(emailId)) {
				validationErrors.put("emailid", "Invalid email format");

			}

			String houseNovalidation = validateHouseNumber(houseNoStr);
			if (houseNovalidation != null) {
				validationErrors.put("houseno", houseNovalidation);
			}

			if (!isValidMobile(mobileNo)) {
				validationErrors.put("mobileno", "Invalid or empty mobile number");
			}

			if (!address.matches("^[a-zA-Z, ]+$") && address.isEmpty() && address.equals(" ")) {
				validationErrors.put("address", "Invalid or empty addres");
			}

			String pincodeValidation = validateNumber(pincodeStr);
			if (pincodeValidation != null) {
				validationErrors.put("pincode", pincodeStr);
			}

			if (!state.matches("^[a-zA-Z, ]+$") && state.isEmpty() && state.equals(" ")) {
				validationErrors.put("state", "Invalid or empty state");
			}

			if (!country.matches("^[a-zA-Z, ]+$") && country.isEmpty() && country.equals(" ")) {
				validationErrors.put("country", "Invalid or empty country");
			}

			String unitConsumptionValidation = validateUnitConsumption(unitConsumedStr);
			if (unitConsumptionValidation != null) {
				validationErrors.put("unit_consumed", unitConsumptionValidation);
			}
            
			// If data is valid we are storing in database
			if (validationErrors.isEmpty()) {

				CustomerDetails customerDetails = new CustomerDetails();
				customerDetails.setcid(Long.parseLong(record.getString("cid")));
				customerDetails.setcustomername(record.getString("customername"));
				customerDetails.setemailid(record.getString("emailid"));
				customerDetails.setmobileno(record.getString("mobileno"));
				customerDetails.sethouseno(Integer.parseInt(record.getString("houseno")));
				customerDetails.setaddress(record.getString("address"));
				customerDetails.setPincode(Long.parseLong(record.getString("pincode")));
				customerDetails.setState(record.getString("state"));
				customerDetails.setCountry(record.getString("country"));
				customerDetailsList.add(customerDetails);
				result = customerService.addBulkCustomerDetails(customerDetailsList);

				BillingDetails billingDetails = new BillingDetails();
				billingDetails.setCustomerID(Long.parseLong(record.getString("cid")));
				billingDetails.setUnitConsumed(Double.parseDouble(record.getString("unit_consumed")));
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Date startDate = dateFormat.parse(record.getString("start_date"));
					Date endDate = dateFormat.parse(record.getString("end_date"));
					Date billDueDate = dateFormat.parse(record.getString("bill_due_date"));
					billingDetails.setStartDate(startDate);
					billingDetails.setEndDate(endDate);
					billingDetails.setBillDueDate(billDueDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				billingDetailsList.add(billingDetails);
				billDetailsService.addBulkBillingDetails(billingDetailsList);
				invoiceController.generateInvoice(billingDetails.getBillingID());
				logger.info("Bulk upload using CSV is successfull");

			}

		});

		if (!validationErrors.isEmpty()) {
			return "ValidationErrors:" + validationErrors.toString();
		}

		if ("Some customers already exist in the database. Bulk customer details added, but duplicates were skipped"
				.equals(result)) {
			return "Some customers already exist in the database. Bulk customer details added, but duplicates were skipped";
		} else {
			return "Customer and Billing details added successfully";
		}

	}

	// A method to validate number
	private String validateNumber(String inputNumber) {
		try {
			long customerId = Long.parseLong(inputNumber);
			if (customerId >= 0) {
				return null;
			} else {
				return "Number must be a positive";
			}
		} catch (NumberFormatException e) {
			return "Invalid Number Format";
		}
	}

	// A method to validate House Number
	private String validateHouseNumber(String houseNoStr) {
		try {
			int houseNumber = Integer.parseInt(houseNoStr);
			if (houseNumber > 0) {
				return null;
			} else {
				return "House number must be a positive integer";
			}
		} catch (NumberFormatException e) {
			return "Invalid house number format";
		}
	}

	// A method to validate Unit consumption
	private String validateUnitConsumption(String unitConsumptionStr) {
		try {
			double unitConsumption = Double.parseDouble(unitConsumptionStr);
			if (unitConsumption >= 0) {
				return null;
			} else {
				return "Unit consumption must be a non-negative number";
			}
		} catch (NumberFormatException e) {
			return "Invalid unit consumption format";
		}
	}

	// A method to validate mobile number
	private boolean isValidMobile(String mobileNo) {
		if (mobileNo.matches("^(\\+\\d{1,3}[- ]?)?\\d{10}$") && !mobileNo.isEmpty() && !mobileNo.equals(" ")) {
			return true;
		} else {
			return false;
		}
	}

	// A method to validate Customer Email
	private boolean isValidEmail(String emailId) {
		if (emailId.matches("^[A-Za-z0-9+_.-]+@(.+)$") && !emailId.equals(" ") && !emailId.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	// A method to validate Customer Name
	private boolean isValidCustomerName(String customerName) {
		if (customerName.matches("^[a-zA-Z. ]+$") && !customerName.equals(" ")) {
			return true;
		} else {
			return false;
		}
	}

}
