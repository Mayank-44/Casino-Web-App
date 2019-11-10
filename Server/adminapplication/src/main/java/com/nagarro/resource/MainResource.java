package com.nagarro.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nagarro.model.Customer;
import com.nagarro.model.RouletteCustomer;
import com.nagarro.service.CustomerService;


/**
 * The Class MainResource.
 */
@Path("customer")
public class MainResource {

	/** The Customer Service. */
	private CustomerService cs = new CustomerService();

	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Customer> getCust() {
		return cs.getCust(); 
	}

	/**
	 * Gets the roulette customer.
	 *
	 * @param uniqueCustomerId the unique customer id
	 * @return the roulette customer
	 */
	@GET
	@Path("roulette/{uniqueCustomerId}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public RouletteCustomer getRouletteCustomer(@PathParam("uniqueCustomerId") String uniqueCustomerId) {
		RouletteCustomer cust = null;
		cust = cs.getRouletteCust(uniqueCustomerId);
		return cust;
	}
	
	/**
	 * Update roulette customer.
	 *
	 * @param uniqueCustomerId the unique customer id
	 * @param accountBalance the account balance
	 * @param blockedAmount the blocked amount
	 * @return the customer
	 */
	@GET
	@Path("roulette/{uniqueCustomerId}/{accountBalance}/{blockedAmount}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Customer updateRouletteCustomer(@PathParam("uniqueCustomerId") String uniqueCustomerId,
			@PathParam("accountBalance") double accountBalance,
			@PathParam("blockedAmount") double blockedAmount) {
		cs.updateRouletteCust(uniqueCustomerId, accountBalance,blockedAmount);
		return null;
	}
	
	/**
	 * Update roulette customer.
	 *
	 * @param uniqueCustomerId the unique customer id
	 * @param rCust the roulette customer
	 * @return the roulette customer
	 */
	@PUT
	@Path("roulette/{uniqueCustomerId}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public RouletteCustomer updateRouletteCustomer(@PathParam("uniqueCustomerId") String uniqueCustomerId, RouletteCustomer rCust) {
		cs.updateRouletteCust(uniqueCustomerId, rCust);
		return rCust;
	}
	
	/**
	 * Gets the customer.
	 *
	 * @param uniqueCustomerId the unique customer id
	 * @return the customer
	 */
	@GET
	@Path("/{uniqueCustomerId}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Customer getCustomer(@PathParam("uniqueCustomerId") String uniqueCustomerId) {
		Customer cust = null;
		cust = cs.getCust(uniqueCustomerId);
		return cust;
	}
	
	/**
	 * Gets the selective customers.
	 *
	 * @param name the name
	 * @param email the email
	 * @param contactno the contactno
	 * @return the selective customers
	 */
	@GET
	@Path("{name}/{email}/{contactno}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Customer> getSelectiveCustomers(@PathParam("name") String name,@PathParam("email") String email
			,@PathParam("contactno") long contactno) {
			return cs.getSelectiveCustomers(name,email,contactno);
	}

	/**
	 * Creates the employee.
	 *
	 * @param cust the customer
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void createCustomer(Customer cust) {
		cs.createCust(cust);
	}

	/**
	 * Update Customer.
	 *
	 * @param cust the customer
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void updateCustomer(Customer cust) {
		cs.updateCust(cust);
	}
}
