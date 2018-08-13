package com.mgmsec.HackMyTeeth.HackMyTeeth.controllers;
import java.time.Clock;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.mgmsec.HackMyTeeth.HackMyTeeth.model.User;
import com.mgmsec.HackMyTeeth.HackMyTeeth.model.Session;
import com.mgmsec.HackMyTeeth.HackMyTeeth.service.PasswordService;

import com.mgmsec.HackMyTeeth.HackMyTeeth.service.UserService;
import com.mgmsec.HackMyTeeth.HackMyTeeth.service.SessionService;
import com.mgmsec.HackMyTeeth.HackMyTeeth.setting.SecuritySettings;

import com.mgmsec.HackMyTeeth.HackMyTeeth.setting.SecurityEnum.*;

@Controller

public class UserController {
	  @Autowired
	    UserService userService;
	    @Autowired
	    SessionService sessService;
	    @Autowired
	    SecuritySettings secSettings;
	    @Autowired
	    PasswordService pwdService;
		@RequestMapping(value="/password",method = RequestMethod.GET)
		public ModelAndView changePage(Model model, HttpServletRequest request) {
			ModelAndView modelAndView = new ModelAndView();
			Cookie loginCookie = sessService.checkLoginCookie(request);
			System.out.println(secSettings.getPwBruteForce());
			String param = request.getParameter("errorMessage");
			if(param != null) {
				modelAndView.addObject("errorMessage",param);
			}
			if (loginCookie != null) {
				Session session = sessService.findBySession(loginCookie.getValue());
				modelAndView.addObject("userName",session.getUsername());
				modelAndView.addObject("role",session.getRole());
				modelAndView.addObject("username",session.getUsername());
				if (session == null) return new ModelAndView("redirect:/login");
			}

			modelAndView.setViewName("password");
			return modelAndView;
		}
		@RequestMapping(value="/changePassword",method = RequestMethod.POST)
		public ModelAndView changeRequest(Model model, HttpServletRequest request) {
			ModelAndView modelAndView = new ModelAndView();
			Cookie loginCookie = sessService.checkLoginCookie(request);
			System.out.println(secSettings.getPwBruteForce());
			int userID = -999;
			String username ="";
			if (loginCookie != null) {
				Session session = sessService.findBySession(loginCookie.getValue());
				userID=(int)session.getUserID();
				username=session.getUsername();
				if (session == null) return new ModelAndView("redirect:/login");
			}
			
			String password = request.getParameter("password");
			String password1 = request.getParameter("password1");
			System.out.println(password + "///" + password1);
			if(password.equals(password1)) {
				switch(secSettings.getPwdStorage()) {
					case Clear:
						break;
					case Hashed:
						password = pwdService.sha256(password);
						break;
				}
				if(userService.changePassword(userID, password,username)) {
					modelAndView.addObject("errorMessage","Password Changed!");
				}
				else
					modelAndView.addObject("errorMessage","Error in changing password");
					
			}
			else {
				modelAndView.addObject("errorMessage","Passwords not match!");
			}
			modelAndView.setViewName("redirect:/password");
			return modelAndView;
		}
	    
}
