package com.nagarro.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author mayankgangwar
 * Class Representing the Customer Entity in the Database
 */
@Entity
@XmlRootElement
public class Customer {

	@Id
	@Column(name="uniqueUserId")
	String uniqueUserId;
	
	@Column(name="customerName")
	String customerName;
	
	@Column(name="dateOfBirth")
	String dateOfBirth;
	
	@Column(name="contactNumber")
	long contactNumber;
	
	@Column(name="emailId")
	String emailId;
	
	@Lob
	@Column(name = "Id", nullable = false, columnDefinition = "longblob")
	private byte[] Id;
	
	@Column(name="accountBalance")
	double accountBalance;
	
	@Column(name="blockedAmount")
	double blockedAmount;

	public String getUniqueUserId() {
		return uniqueUserId;
	}

	public void setUniqueUserId(String uniqueUserId) {
		this.uniqueUserId = uniqueUserId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public byte[] getId() {
		return Id;
	}

	public void setId(byte[] id) {
		Id = id;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public double getBlockedAmount() {
		return blockedAmount;
	}

	public void setBlockedAmount(double blockedAmount) {
		this.blockedAmount = blockedAmount;
	}

	@Override
	public String toString() {
		return "Customer [uniqueUserId=" + uniqueUserId + ", customerName=" + customerName + ", dateOfBirth="
				+ dateOfBirth + ", contactNumber=" + contactNumber + ", emailId=" + emailId + ", Id="
				+ Arrays.toString(Id) + ", accountBalance=" + accountBalance + ", blockedAmount=" + blockedAmount + "]";
	}
	
}
