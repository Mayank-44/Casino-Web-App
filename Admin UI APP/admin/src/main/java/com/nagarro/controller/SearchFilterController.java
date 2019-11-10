package com.nagarro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.model.Customer;
import com.nagarro.service.CustomerService;

/**
 * 
 * @author mayankgangwar
 * This class handles the Filtered Search Request for Customer
 */
@RestController
public class SearchFilterController {
	@Autowired
	CustomerService cs;
	/**
	 * 
	 * @param name: Customer Name
	 * @param contact: Customer Contact Number
	 * @param email: Customer Email
	 * @return: ModelAndView object which returns the customer list and redirect to UserList JSP page.
	 */
	@RequestMapping(value = "SearchFilter", method = RequestMethod.GET)
	public ModelAndView UserList(@RequestParam("Name") String name, @RequestParam("Contact") String contact,
								@RequestParam("Email") String email) {
		ModelAndView mv = new ModelAndView();
		List<Customer> customers = null;
		customers = cs.getSelectiveCustomers(name, email, contact);
		mv.setViewName("UserList.jsp");
		mv.addObject("customers", customers);
		return mv;
	}
}
