package com.nagarro.model;

/**
 * The Class CustomerResponse.
 */
public class CustomerResponse {

	/** The status code. */
	private int statusCode;
	
	/** The random number. */
	private double randomNumber;
	
	/** The winning amount. */
	private double winningAmount;

	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets the status code.
	 *
	 * @param statusCode the new status code
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Gets the random number.
	 *
	 * @return the random number
	 */
	public double getRandomNumber() {
		return randomNumber;
	}

	/**
	 * Sets the random number.
	 *
	 * @param randomNumber the new random number
	 */
	public void setRandomNumber(double randomNumber) {
		this.randomNumber = randomNumber;
	}

	/**
	 * Gets the winning amount.
	 *
	 * @return the winning amount
	 */
	public double getWinningAmount() {
		return winningAmount;
	}

	/**
	 * Sets the winning amount.
	 *
	 * @param winningAmount the new winning amount
	 */
	public void setWinningAmount(double winningAmount) {
		this.winningAmount = winningAmount;
	}

}
