package com.mgmsec.HackMyTeeth.HackMyTeeth.controllers;

import java.time.Clock;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import com.mgmsec.HackMyTeeth.HackMyTeeth.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.mgmsec.HackMyTeeth.HackMyTeeth.model.User;
import com.mgmsec.HackMyTeeth.HackMyTeeth.model.Appointment;
import com.mgmsec.HackMyTeeth.HackMyTeeth.model.Session;

import com.mgmsec.HackMyTeeth.HackMyTeeth.setting.SecuritySettings;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
//@Scope("session")
public class LoginController {
	@Autowired
	UserService userService;
	@Autowired
	AppointmentService appService;
	@Autowired
	SessionService sessService;
	@Autowired
	SecuritySettings secSettings;
	@Autowired
	PasswordService pwdService;
	@Autowired
	CapchaService capchaService;

	@RequestMapping("/login")
	public ModelAndView firstPage(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Cookie loginCookie = sessService.checkLoginCookie(request);
		System.out.println(secSettings.getPwBruteForce());
		if (loginCookie != null) {
			Session session = sessService.findBySession(loginCookie.getValue());
			if (session != null) return new ModelAndView("redirect:/home");
		}
		System.out.println(secSettings.getPwBruteForce());
		switch (secSettings.getPwBruteForce()){
			case Captcha:
				System.out.println("dsdsds");
					model.addAttribute("isCaptchaEnabled",true);
					break;
				default:
					break;
		}
		modelAndView.setViewName("login");
		return modelAndView;
	}
	@RequestMapping("/")
	public ModelAndView slash() {
		return new ModelAndView("redirect:/login");
	}
	@RequestMapping("/home")
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Cookie loginCookie = sessService.checkLoginCookie(request);
		System.out.println("adsadasd    " + loginCookie);
		if(loginCookie != null) {
			System.out.println("Login Cookie is: " +loginCookie.getValue());

			Session sessions = sessService.findBySession(loginCookie.getValue());
			if (sessions != null) {

				System.out.println("session is" + sessions);
				List<User> listDentist = userService.listDentist();
				System.out.println(listDentist);
				for (User e: listDentist) {
					System.out.println(e.toString());
				}
				modelAndView.addObject("listDentist",listDentist);
				modelAndView.addObject("role",sessions.getRole());
				modelAndView.addObject("username",sessions.getUsername());
				modelAndView.setViewName("home");
			}
			else {
				return new ModelAndView("redirect:/login");

			}
		}
		else {
			return new ModelAndView("redirect:/login");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/listappointment",method = RequestMethod.GET)
	public ModelAndView listappointment(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Cookie loginCookie = sessService.checkLoginCookie(request);
		if(loginCookie != null) {
			
			System.out.println("Login Cookie is: " +loginCookie.getValue());
			
			Session sessions = sessService.findBySession(loginCookie.getValue());
			if (sessions != null) {
				System.out.println("session is" + sessions);
				if (sessions.getRole()==1) {
					List<Appointment> listapp = appService.findAll(sessions.getUsername());
					System.out.println(listapp);
					for (Appointment e: listapp) {
						System.out.println(e.toString());
					}
					modelAndView.addObject("listapp",listapp);
					modelAndView.addObject("role",sessions.getRole());
					modelAndView.addObject("username",sessions.getUsername());
					modelAndView.setViewName("listappointment");
				}else {
					return new ModelAndView("redirect:/home");
				}
			}
			else {
				return new ModelAndView("redirect:/login");
			}
		}
		else {
			return new ModelAndView("redirect:/home");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String LogoutPage(HttpServletRequest request, HttpServletResponse response) {
		Cookie loginCookie =  sessService.checkLoginCookie(request);
		if (loginCookie != null) {
            loginCookie.setMaxAge(0);
            response.addCookie(loginCookie);
            sessService.delSession(loginCookie.getValue());
        }
		
		return "redirect:/login"; 
	}

	@RequestMapping(value = "/loginVal2" , method = RequestMethod.POST)
	public ModelAndView login2(HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String g_recaptcha_response = request.getParameter("g-recaptcha-response");
		String ip = request.getRemoteAddr();
		switch(secSettings.getPwdStorage()) {
			case Clear:
				break;
			case Hashed:
				password = pwdService.sha256(password);
				break;
		}
		switch (secSettings.getPwBruteForce()){
			case Captcha:
				model.addAttribute("isCaptchaEnabled",true);
				System.out.println(g_recaptcha_response  +  "---------------");
				if(!capchaService.verifyResponse(g_recaptcha_response, ip)) {
					modelAndView.addObject("errorMessage", "Check your captcha");
					modelAndView.setViewName("login");
					return modelAndView;
				}

				break;
			default:
				break;
		}
		System.out.println(password);
		List<User> get = userService.findByUser(username, password);
		
		System.out.print(get);
		if(get == null || get.size() == 0) {
			modelAndView.addObject("errorMessage", "Invalid username or password");
			modelAndView.setViewName("login");
		}else if(get.get(0).getRole().equals("1")) {
			setSessionCookie(request,response,get.get(0).getUsername(),Integer.parseInt(get.get(0).getRole()),(int) get.get(0).getuserID());
			return new ModelAndView("redirect:/listappointment");

		}else if(get.get(0).getRole().equals("0")){
			setSessionCookie(request,response,get.get(0).getUsername(),Integer.parseInt(get.get(0).getRole()),(int) get.get(0).getuserID());
			return new ModelAndView("redirect:/home");

		}
		return modelAndView;

	}
	private void setSessionCookie(HttpServletRequest request, HttpServletResponse response, String username, int role, int userID ) {
		String sessionid = null;
		switch(secSettings.getSessFix()) {
			case Yes:
				sessionid = sessService.addSession(userID, username, role,"");
				break;
			case No:
				Cookie loginCookie =  sessService.checkLoginCookie(request);
				if (loginCookie != null) {
		            sessionid = loginCookie.getValue();
		            sessionid = sessService.addSession(userID, username, role,sessionid);
		        }
				else {
					sessionid = sessService.addSession(userID, username, role,"");
				
				}
				break;
		}
		Cookie loginCookie = new Cookie("SESSIONCOOKIE", sessionid);
		switch(secSettings.getCookParam()) {
			case True:
				loginCookie.setHttpOnly(true);
				break;
			case False:
				break;
		}
		response.addCookie(loginCookie);
		
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
			return new ModelAndView("redirect:/home");
		} else if (reUser.equals(s2)) {
			
		}
		return modelAndView;
	}
}