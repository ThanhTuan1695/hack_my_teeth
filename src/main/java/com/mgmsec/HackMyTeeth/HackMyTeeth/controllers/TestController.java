package com.mgmsec.HackMyTeeth.HackMyTeeth.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mgmsec.HackMyTeeth.HackMyTeeth.service.CustomerService;

@Controller
public class TestController {
	private CustomerService customerService;
	@RequestMapping("/welcome")
	public ModelAndView firstPage() {
		return new ModelAndView("welcome");
	}
	@RequestMapping(value = "/displayCustomer", method = RequestMethod.GET)
	public String list(Map<String, Object> map) {
		map.put("userList",customerService.findAll());
		return "list";
	}

}