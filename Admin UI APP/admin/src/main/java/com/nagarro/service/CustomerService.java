package com.nagarro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import java.util.UUID;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.nagarro.model.Customer;

/**
 * 
 * @author mayankgangwar
 * Customer Service Class that connects to server for fetching Customer Data.
 */
@Component
public class CustomerService {

	// Server URL
	private final String serverURL = "http://localhost:8080/adminapplication/webapi/customer/";
	
	/**
	 * Method to return list of all customers.
	 * @return : List of Customers
	 */
	public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		try {
			String getCustomersUrl = serverURL;
			RestTemplate restTemplate = new RestTemplate();

			ResponseEntity<List<Customer>> response = restTemplate.exchange(getCustomersUrl, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Customer>>() {
					});
			customers = response.getBody();
		} catch (Exception e) {
			System.out.println("Can't retrieve Customer List from server.");
		}
		return customers;
	}
	
	/**
	 * Method to return list of filtered customers
	 * @param name: Customer Name
	 * @param email: Customer Email
	 * @param contactno: Customer Contact No
	 * @return: List of Customers
	 */
	public List<Customer> getSelectiveCustomers(String name,String email,String contactno) {

		RestTemplate restTemplate = new RestTemplate();
		List<Customer> customers = null;
		try {
			long number;
			if(name.equals(""))
				name="1";
			if(email.equals(""))
				email="1";
			try {
				number = Long.parseLong(contactno);
			}
			catch(Exception e){
				number = 0;
			}
			String getCustomersUrl = serverURL + name + "/" + email + "/" + number;
			ResponseEntity<List<Customer>> response = restTemplate.exchange(getCustomersUrl, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Customer>>() {
					});
			customers = response.getBody();
		} catch (Exception e) {
			System.out.println("Can't retrieve filtered Customer List from server.");
		} 
		return customers;
	}

	/**
	 * Method to return the requested customer
	 * @param uniqueCustomerId: Customer Unique Id
	 * @return: Customer
	 */
	public Customer getCustomer(String uniqueCustomerId) {
		RestTemplate restTemplate = new RestTemplate();
		Customer cust = null;
		String getCust = serverURL + uniqueCustomerId;
		try {
			cust = restTemplate.getForObject(getCust, Customer.class);
		}
		catch(Exception e) {
			System.out.println("Customer not retrieved from server.");
		}
		
		return cust;
	}
	
	/**
	 * Method to update the Recharge value of Customer
	 * @param uniqueCustomerId: Customer Unique Id
	 * @param Recharge: Amount to be Recharged
	 */
	public void editCustomer(String uniqueCustomerId, double Recharge) {
		try {
			Customer cust = getCustomer(uniqueCustomerId);
			cust.setAccountBalance(cust.getAccountBalance() + Recharge);
			RestTemplate restTemplate = new RestTemplate();
			String editCustomerUrl = serverURL;
			
			restTemplate.put(editCustomerUrl, cust);
			
		}catch(Exception e) {
			System.out.println("Not able to update Customer Data, Server Problem.");
		}
	}

	/**
	 * Method to Upload a new customer to the database
	 * @param name: Customer Name
	 * @param dob: Customer Date Of Birth
	 * @param contact: Customer Contact No.
	 * @param email: Customer Email.
	 * @param image: Customer Image.
	 */
	public void uploadCustomer(String name, String dob, long contact, String email, byte[] image) {
		Customer cust = new Customer();
		cust.setAccountBalance(500);
		cust.setBlockedAmount(0);
		cust.setContactNumber(contact);
		cust.setCustomerName(name);
		cust.setDateOfBirth(dob);
		cust.setEmailId(email);
		cust.setId(image);
		
		String uuid = UUID.randomUUID().toString().replace("-", "");
		
		cust.setUniqueUserId(uuid);
		
		RestTemplate restTemplate = new RestTemplate();
		String registerCustUrl = serverURL;
		try {
			restTemplate.postForLocation(registerCustUrl, cust);
		} catch (Exception e) {
			System.out.println("Not able to upload Customer data. Server Problem.");
		}
	}
}
