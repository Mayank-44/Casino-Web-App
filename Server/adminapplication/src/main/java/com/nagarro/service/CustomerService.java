package com.nagarro.service;

import java.util.List;

import com.nagarro.model.Customer;
import com.nagarro.model.RouletteCustomer;
import com.nagarro.repository.CustomerRepository;

/**
 * The Class CustomerService.
 */
public class CustomerService {

	/** The customer repo. */
	CustomerRepository custRepo = new CustomerRepository();
	
	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public List<Customer> getCust() {
		return custRepo.getCustomers();
	}

	/**
	 * Creates the customer.
	 *
	 * @param cust the customer
	 */
	public void createCust(Customer cust) {
		custRepo.create(cust);
	}

	/**
	 * Update customer.
	 *
	 * @param cust the customer
	 */
	public void updateCust(Customer cust) {
		custRepo.update(cust);
	}

	/**
	 * Gets the customer.
	 *
	 * @param uniqueCustomerId the unique customer id
	 * @return the customer
	 */
	public Customer getCust(String uniqueCustomerId) {
		return custRepo.getCustomer(uniqueCustomerId);
	}

	/**
	 * Gets the selective customers.
	 *
	 * @param name the name
	 * @param email the email
	 * @param contactno the contactno
	 * @return the selective customers
	 */
	public List<Customer> getSelectiveCustomers(String name, String email, long contactno) {
		return custRepo.getSelectiveCustomers(name,email,contactno);
	}

	/**
	 * Gets the roulette customer.
	 *
	 * @param uniqueCustomerId the unique customer id
	 * @return the roulette customer
	 */
	public RouletteCustomer getRouletteCust(String uniqueCustomerId) {
		return custRepo.getRouletteCustomer(uniqueCustomerId);
	}

	/**
	 * Update roulette customer.
	 *
	 * @param uniqueCustomerId the unique customer id
	 * @param rCust the roulette customer
	 */
	public void updateRouletteCust(String uniqueCustomerId, RouletteCustomer rCust) {
		Customer cust = new Customer();
		cust = getCust(uniqueCustomerId);
		cust.setAccountBalance(rCust.getAccountBalance());
		cust.setBlockedAmount(rCust.getBlockedAmount());
		custRepo.update(cust);
	}

	/**
	 * Update roulette customer.
	 *
	 * @param uniqueCustomerId the unique customer id
	 * @param accountBalance the account balance
	 * @param blockedAmount the blocked amount
	 */
	public void updateRouletteCust(String uniqueCustomerId,double accountBalance,double blockedAmount) {
		Customer cust = new Customer();
		cust = getCust(uniqueCustomerId);
		cust.setAccountBalance(accountBalance);
		cust.setBlockedAmount(blockedAmount);
		custRepo.update(cust);
	}

}
