package com.mgmsec.HackMyTeeth.HackMyTeeth.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.mgmsec.HackMyTeeth.HackMyTeeth.model.Customer;
import com.mgmsec.HackMyTeeth.HackMyTeeth.service.CustomerService;

@Controller
public class TestController {
	@Autowired
	CustomerService customerService;
	@RequestMapping("/welcome")
	public ModelAndView firstPage() {
		return new ModelAndView("welcome");
	}
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("userList",customerService.findAll());
		
		for (Customer cust : customerService.findAll()) {
		    System.out.println(cust.toString());
		}

		return "list";
	}
	@RequestMapping("/home")
	public ModelAndView homePage(){
		return new ModelAndView("home");
	}

}