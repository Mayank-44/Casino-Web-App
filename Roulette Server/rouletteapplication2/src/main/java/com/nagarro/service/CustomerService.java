package com.nagarro.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import com.nagarro.model.Customer;
import com.nagarro.model.CustomerResponse;
import com.nagarro.repository.CustomerRepository;

/**
 * The Class CustomerService.
 */
public class CustomerService {

	/** The customer repository. */
	CustomerRepository custRepo = new CustomerRepository();

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
	 * Update customer.
	 *
	 * @param uniqueCustomerId the unique customer id
	 * @param cust the customer
	 */
	public void updateCust(String uniqueCustomerId, Customer cust) {
		System.out.println("service");
		custRepo.updateCust(uniqueCustomerId, cust);
	}

	/**
	 * Update roulette customer.
	 *
	 * @param uniqueCustomerId the unique customer id
	 * @param bettingOption the betting option
	 * @param bettingValue the betting value
	 * @return the customer response
	 */
	public CustomerResponse updateRouletteCust(String uniqueCustomerId, String bettingOption, double bettingValue) {
		Customer cust = null;
		CustomerResponse custResponse = new CustomerResponse();
		cust = getCust(uniqueCustomerId);
		if (!isBettingAmountGreater(cust, bettingValue))
		{
			custResponse.setStatusCode(-1);
			return custResponse;
		}
		else {
			double updatedAccountValue = blockAmount(cust, bettingValue);
			cust.setAccountBalance(updatedAccountValue);
			cust.setBlockedAmount(cust.getBlockedAmount() + bettingValue);
			updateCust(uniqueCustomerId, cust);
			cust = getCust(uniqueCustomerId);
			int randomNumber = getRandomNumber();
			double winningAmountValue = calculateWinningAmount(bettingValue, bettingOption,randomNumber);
			
			if(winningAmountValue == -1)
			{
				cust.setBlockedAmount(cust.getBlockedAmount() - bettingValue);
				updateCust(uniqueCustomerId,cust);
				custResponse.setRandomNumber(randomNumber);
				custResponse.setStatusCode(-2);
				
				return custResponse;
			}
			else
			{
				updatedAccountValue = winningAmountValue + cust.getAccountBalance() + bettingValue;
				cust.setBlockedAmount(cust.getBlockedAmount()-bettingValue);
				cust.setAccountBalance(updatedAccountValue);
				updateCust(uniqueCustomerId,cust);
				custResponse.setStatusCode(1);
				custResponse.setRandomNumber(randomNumber);
				custResponse.setWinningAmount(winningAmountValue);
				return custResponse;
			}
		}
	}

	/**
	 * Checks if is betting amount greater.
	 *
	 * @param cust the customer
	 * @param bettingValue the betting value
	 * @return true, if is betting amount greater
	 */
	public boolean isBettingAmountGreater(Customer cust, double bettingValue) {
		if (cust.getAccountBalance() < bettingValue)
			return false;

		return true;
	}

	/**
	 * Block amount.
	 *
	 * @param cust the customer
	 * @param bettingValue the betting value
	 * @return the double
	 */
	public double blockAmount(Customer cust, double bettingValue) {
		return cust.getAccountBalance() - bettingValue;
	}

	/**
	 * Calculate winning amount.
	 *
	 * @param bettingValue the betting value
	 * @param bettingOption the betting option
	 * @param randomNumber the random number
	 * @return the double
	 */
	public double calculateWinningAmount(double bettingValue, String bettingOption, int randomNumber) {
		double multiplier = raisingFactor(bettingOption);

		boolean winner = isWinner(bettingOption, randomNumber);
		
		if(!winner)
			return -1;
		else
		{
			return multiplier * bettingValue;
		}
	}

	/**
	 * Gets the random number.
	 *
	 * @return the random number
	 */
	public int getRandomNumber() {
		ArrayList<Long> randomNumberList = new ArrayList<Long>();
		for (int i = 0; i < 20; i++) {
			int lengthRandomNumber = ThreadLocalRandom.current().nextInt(1, 11);
			String temp = "";
			for (int j = 0; j < lengthRandomNumber; j++) {
				int randomDigit = ThreadLocalRandom.current().nextInt(0, 10);
				temp = temp + randomDigit;

			}
			long randomNew = Long.parseLong(temp);
			randomNumberList.add(randomNew);
		}
		Collections.sort(randomNumberList);
		long firstListSum = 0, secondListSum = 0;
		int i = 0, firstSumIndex = 0, secondSumIndex = 0;
		for (i = randomNumberList.size() - 1; i > 0; i--) {
			if (firstSumIndex < 10 && secondSumIndex < 10) {
				if (firstListSum >= secondListSum) {
					firstListSum += randomNumberList.get(i - 1);
					secondListSum += randomNumberList.get(i);
					firstSumIndex++;
					secondSumIndex++;
				} else {
					firstListSum += randomNumberList.get(i);
					secondListSum += randomNumberList.get(i - 1);
					firstSumIndex++;
					secondSumIndex++;
				}
			} else if (secondSumIndex >= 10) {
				firstListSum += randomNumberList.get(i);
				firstSumIndex++;
			} else {
				secondListSum += randomNumberList.get(i);
				secondSumIndex++;
			}
		}
		return (int) ((Math.abs(firstListSum - secondListSum)) % 37);
	}

	/**
	 * Checks if is winner.
	 *
	 * @param bettingOption the betting option
	 * @param randomNumber the random number
	 * @return true, if is winner
	 */
	public boolean isWinner(String bettingOption,double randomNumber){
	    boolean inRange = false;

	    switch (bettingOption) {
	      case "firstTwelve": if (randomNumber >= 1 && randomNumber <= 12)
							        inRange = true;
							        break;
	      case "secondTwelve": if (randomNumber >= 13 && randomNumber <= 24)
	        inRange = true;
	        break;
	      case "thirdTwelve": if (randomNumber >= 25 && randomNumber <= 36)
	        inRange = true;
	        break;
	      case "zero": if (randomNumber == 0)
	        inRange = true;
	        break;
	      case "onetoeighteen": if (randomNumber >= 1 && randomNumber <= 18)
	        inRange = true;
	        break;
	      case "nineteenthtothirtysix": if (randomNumber >= 19 && randomNumber <= 36)
	        inRange = true;
	        break;
	      case "even": if (randomNumber % 2 == 0)
	        inRange = true;
	        break;
	      case "odd": if (randomNumber % 2 != 0)
	        inRange = true;
	        break;
	    }

	    return inRange;
	  }

	/**
	 * Raising factor.
	 *
	 * @param bettingOption the betting option
	 * @return the double
	 */
	public double raisingFactor(String bettingOption) {
		double multiple = 1;

		switch (bettingOption) {
		case "firstTwelve":
			multiple = 1.5;
			break;
		case "secondTwelve":
			multiple = 1.5;
			break;
		case "thirdTwelve":
			multiple = 1.5;
			break;
		case "zero":
			multiple = 10;
			break;
		case "onetoeighteen":
			multiple = 1.25;
			break;
		case "nineteenthtothirtysix":
			multiple = 1.25;
			break;
		case "even":
			multiple = 1.25;
			break;
		case "odd":
			multiple = 1.25;
			break;
		}
		return multiple;
	}
}
