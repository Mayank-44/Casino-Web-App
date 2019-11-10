package com.nagarro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.model.Customer;
import com.nagarro.service.CustomerService;

/**
 * 
 * @author mayankgangwar
 * Class to return the UserList to UserList JSP page.
 */
@RestController
public class UserListController {
	@Autowired
	CustomerService cs;
	/**
	 * 
	 * @return: ModelAndView object containing the Customer lists to UserList JSP page.
	 */
	@RequestMapping(value = "UserList", method = RequestMethod.GET)
	public ModelAndView UserList() {
		ModelAndView mv = new ModelAndView();
		List<Customer> customers = null;
		customers = cs.getCustomers();
		mv.setViewName("UserList.jsp");
		mv.addObject("customers", customers);
		return mv;
	}
}
