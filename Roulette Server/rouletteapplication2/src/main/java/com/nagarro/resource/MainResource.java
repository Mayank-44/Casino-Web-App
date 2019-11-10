package com.nagarro.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nagarro.model.Customer;
import com.nagarro.model.CustomerResponse;
import com.nagarro.service.CustomerService;

/**
 * The Class MainResource.
 */
@Path("customer")
public class MainResource {

	/** The customerService. */
	private CustomerService cs = new CustomerService();

	/**
	 * Gets the Customer.
	 *
	 * @param uniqueCustomerId the unique customer id
	 * @return the employee
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
	 * Update Customer.
	 *
	 * @param uniqueCustomerId the unique customer id
	 * @param cust the customer
	 */
	@PUT
	@Path("/{uniqueCustomerId}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN  })
	public void updateCustomer(@PathParam("uniqueCustomerId") String uniqueCustomerId,Customer cust) {
		cs.updateCust(uniqueCustomerId,cust);
	}
	
	/**
	 * Update roulette Customer.
	 *
	 * @param uniqueCustomerId the unique customer id
	 * @param bettingOption the betting option
	 * @param bettingValue the betting value
	 * @param cust the cust
	 * @return the customer response
	 */
	@PUT
	@Path("/{uniqueCustomerId}/{bettingOption}/{bettingValue}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public CustomerResponse updateRouletteCustomer(@PathParam("uniqueCustomerId") String uniqueCustomerId,
			@PathParam("bettingOption") String bettingOption, @PathParam("bettingValue") double bettingValue,
			Customer cust) {
		return cs.updateRouletteCust(uniqueCustomerId,bettingOption,bettingValue);
	}
}
