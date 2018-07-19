package com.mgmsec.HackMyTeeth.HackMyTeeth.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.mgmsec.HackMyTeeth.HackMyTeeth.model.User;
import com.mgmsec.HackMyTeeth.HackMyTeeth.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
	@RequestMapping("/welcome")
	public ModelAndView firstPage() {
		return new ModelAndView("welcome");
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		String name = user.getUsername();
		String pass =user.getPassword();
		if(name == null || name.isEmpty()) {
			modelAndView.addObject("errorMessage", "Please enter username");
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		if(pass == null || pass.isEmpty()) {
			modelAndView.addObject("errorMessage", "Please enter password");
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		boolean isValidUser = userService.validateUser(name, pass);
		if (!isValidUser) {
			modelAndView.addObject("errorMessage", "Invalid username or password");
			return new ModelAndView("redirect:/welcome");
		}
		return modelAndView;
	}

}