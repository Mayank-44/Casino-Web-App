package com.nagarro.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.model.Customer;
import com.nagarro.model.RouletteCustomer;
import com.nagarro.service.SessionProvider;


/**
 * The Class CustomerRepository.
 */
public class CustomerRepository {

	
	/**
	 * Gets the customers.
	 *
	 * @return the customers
	 */
	public List<Customer> getCustomers() {
		Session session = SessionProvider.getSession();
		List<Customer> custList= new ArrayList<>();
		try {
			String query = "from Customer";
			TypedQuery<Customer> q = session.createQuery(query);
			custList = (ArrayList) q.getResultList();
		}
		catch(Exception e)
		{
			System.out.println("Can't retrieve Customer List from Database");
		}
		finally {
			session.close();
		}
		return custList;
	}
	
	/**
	 * Gets the customer.
	 *
	 * @param uniqueCustomerId the unique customer id
	 * @return the customer
	 */
	public Customer getCustomer(String uniqueCustomerId) {
		Session session = SessionProvider.getSession();
		Customer cust = null;
		try {
			Transaction tns = session.beginTransaction();
			cust = (Customer) session.get(Customer.class, uniqueCustomerId);
			tns.commit();
		}
		catch(Exception e) {
			System.out.println("Not able to retrieve customer from Database");
		}
		finally {
			session.close();
		}
		return cust;
	}
	
	/**
	 * Creates the.
	 *
	 * @param cust the cust
	 */
	public void create(Customer cust) {
		Session session = SessionProvider.getSession();
		try {
			
			Transaction tns = session.beginTransaction();
			session.save(cust);
			tns.commit();
		}
		catch(Exception e) {
			System.out.println("Not able to Create Customer in Database");
		}
		finally {
			session.close();
		}
	}

	/**
	 * Update.
	 *
	 * @param cust the cust
	 */
	public void update(Customer cust) {
		try {
			Session session = SessionProvider.getSession();
			Transaction tns = session.beginTransaction();
			session.update(cust);
			tns.commit();
		}
		catch(Exception e) {
			System.out.println("Not able to update Customer details in Database");
		}
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
		Session session = SessionProvider.getSession();
		List<Customer> custList= new ArrayList<>();
		
		String query = "from Customer where ";
				
		if(!name.equals("1"))
			query = query + "customerName like '%" + name + "%' and ";
		
		if(!email.equals("1"))
			query = query + "emailId like '%" + email + "%' and ";
		
		if(contactno == 0)
			query = query + "contactNumber like '%'";
		else
			query = query + "contactNumber like '%" + contactno + "%'";
		
		try {
			TypedQuery<Customer> q = session.createQuery(query);
			custList = (ArrayList<Customer>) q.getResultList();
		}
		catch (Exception e) {
			System.out.println("Not able to filter Customer Details from Database");
		}
		return custList;
	}

	/**
	 * Gets the roulette customer.
	 *
	 * @param uniqueCustomerId the unique customer id
	 * @return the roulette customer
	 */
	public RouletteCustomer getRouletteCustomer(String uniqueCustomerId) {
		Session session = SessionProvider.getSession();
		RouletteCustomer cust = new RouletteCustomer();
		Customer c = new Customer();
		try {
			String query = "from Customer where uniqueUserId = '"+uniqueCustomerId+"'";
			TypedQuery<Customer> q = session.createQuery(query);
			c = (Customer)q.getSingleResult();
		}
		catch(Exception e) {
			System.out.println("No Entity found.");
		}
		cust.setAccountBalance(c.getAccountBalance());
		cust.setBlockedAmount(c.getBlockedAmount());
		cust.setCustomerName(c.getCustomerName());
		session.close();
		return cust;
	}
}
