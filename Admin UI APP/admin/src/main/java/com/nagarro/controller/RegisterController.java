package com.nagarro.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.service.CustomerService;

/**
 * 
 * @author mayankgangwar Class to handle the Customer Registration Request.
 */
@RestController
public class RegisterController {

	@Autowired
	CustomerService custServ;

	/**
	 * 
	 * @param request: HttpServletRequest object
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping("register")
	public ModelAndView create(HttpServletRequest request) {
		String name = null, dob = null, email = null;
		long contact = 0;
		ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
		List<FileItem> items = null;
		try {
			items = sf.parseRequest(request);

			FileItem image = null;
			for (FileItem item : items) {
				if (item.isFormField()) {

					if (item.getFieldName().equals("name")) {
						name = item.getString();
					}
					if (item.getFieldName().equals("dob")) {
						dob = item.getString();
					}
					if (item.getFieldName().equals("email")) {
						email = item.getString();
					}
					if (item.getFieldName().equals("contact")) {
						contact = Long.parseLong(item.getString());
					}
				} else {
					image = item;
				}
			}
			custServ.uploadCustomer(name, dob, contact, email, image.get());
		} catch (FileUploadException e) {
			System.out.println("unable to parse request");
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Home.jsp");
		return mv;
	}

}
