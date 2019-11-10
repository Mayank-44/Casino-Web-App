package com.nagarro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.service.CustomerService;

/**
 * 
 * @author mayankgangwar
 *	Class to handle the customer recharge request.
 */
@RestController
public class RechargeController {

	@Autowired
	CustomerService cs;
	/**
	 * 
	 * @param uniqueCustomerId: customer unique Id
	 * @param Recharge: Recharge amount to be added to account balance
	 * @return : return ModelAndView object that redirects it to UserList JSP page
	 */
	@RequestMapping(value = "Recharge", method = RequestMethod.POST)
	public ModelAndView UserList(@RequestParam("uniqueUserId") String uniqueCustomerId, 
								@RequestParam("recharge") double Recharge) {
		cs.editCustomer(uniqueCustomerId, Recharge);
		return new ModelAndView("redirect:UserList");
	}
	
}
