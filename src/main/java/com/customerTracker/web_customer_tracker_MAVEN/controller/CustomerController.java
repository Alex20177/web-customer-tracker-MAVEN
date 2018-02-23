package com.customerTracker.web_customer_tracker_MAVEN.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.customerTracker.Service.CustomerService;
import com.customerTracker.entities.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String customerList(Model model) {
		
		List<Customer> customers = customerService.getCustomer();
		model.addAttribute("customers", customers);
		return "list-customers";
		
	}//Close customerList method.

}//Close CustomerController class.
