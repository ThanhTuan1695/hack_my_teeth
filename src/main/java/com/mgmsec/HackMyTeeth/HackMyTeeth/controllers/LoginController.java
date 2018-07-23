package com.mgmsec.HackMyTeeth.HackMyTeeth.controllers;

import java.time.Clock;
import java.util.List;
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
	@RequestMapping("/login")
	public ModelAndView firstPage() {
		return new ModelAndView("login");
	}
	
	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		List<User> listDentist = userService.listDentist();
		System.out.println(listDentist);
		for (User e: listDentist) {
			System.out.println(e.toString());
		}
		modelAndView.addObject("listDentist",listDentist);
		modelAndView.setViewName("home");
		return modelAndView;
	}
	@RequestMapping("/welcome")
	public ModelAndView welcome() {
		return new ModelAndView("welcome");
	}
	
	@RequestMapping(value = "/loginVal", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		String name = user.getUsername();
		String pass =user.getPassword();
		modelAndView.setViewName("login");
		
		String reUser = userService.findByUsername(name, pass);
		String s1 = "customer";
		String s2 = "dentist";
		String s3 = "invalid";
		if (reUser.equals(s3)) {
			modelAndView.addObject("errorMessage", "Invalid username or password");
			modelAndView.setViewName("login");
			return modelAndView;
		} else if (reUser.equals(s1)) {
			return new ModelAndView("redirect:/welcome");
		} else if (reUser.equals(s2)) {
			
		}
		return modelAndView;
	}
}