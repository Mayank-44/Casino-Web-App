package com.nagarro.model;

/**
 * The Class RouletteCustomer.
 */
public class RouletteCustomer {
	
	/** The customer name. */
	String customerName;
	
	/** The account balance. */
	double accountBalance;
	
	/** The blocked amount. */
	double blockedAmount;

	/**
	 * Gets the customer name.
	 *
	 * @return the customer name
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Sets the customer name.
	 *
	 * @param customerName the new customer name
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Gets the account balance.
	 *
	 * @return the account balance
	 */
	public double getAccountBalance() {
		return accountBalance;
	}

	/**
	 * Sets the account balance.
	 *
	 * @param accountBalance the new account balance
	 */
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	/**
	 * Gets the blocked amount.
	 *
	 * @return the blocked amount
	 */
	public double getBlockedAmount() {
		return blockedAmount;
	}

	/**
	 * Sets the blocked amount.
	 *
	 * @param blockedAmount the new blocked amount
	 */
	public void setBlockedAmount(double blockedAmount) {
		this.blockedAmount = blockedAmount;
	}
}
