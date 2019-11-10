package com.nagarro.repository;

import org.springframework.web.client.RestTemplate;

import com.nagarro.model.Customer;

/**
 * The Class CustomerRepository.
 */
public class CustomerRepository {

	/** The server URL. */
	private final String serverURL = "http://localhost:8080/adminapplication/webapi/customer/roulette/";
	
	/**
	 * Gets the customer.
	 *
	 * @param uniqueCustomerId the unique customer id
	 * @return the customer
	 */
	public Customer getCustomer(String uniqueCustomerId) {
		RestTemplate restTemplate = new RestTemplate();
		Customer cust = new Customer();
		String getCust = serverURL + uniqueCustomerId;
		try {
			cust = restTemplate.getForObject(getCust, Customer.class);
		}
		catch(Exception e ) {
			System.out.println("Not able to retrieve Customer from server");
		}

		return cust;
	}

	/**
	 * Update customer.
	 *
	 * @param uniqueCustomerId the unique customer id
	 * @param cust the customer
	 * @return the customer
	 */
	public Customer updateCust(String uniqueCustomerId, Customer cust) {
		RestTemplate restTemplate = new RestTemplate();
		String updateCustomerUrl = serverURL + uniqueCustomerId;
		try{
			restTemplate.put(updateCustomerUrl, cust);
		}
		catch(Exception e) {
			System.out.println("Not able to update customer. Server Problem");
		}
		return cust;
	}
}
