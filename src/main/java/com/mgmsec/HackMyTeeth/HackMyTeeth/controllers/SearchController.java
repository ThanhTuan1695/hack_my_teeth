package com.mgmsec.HackMyTeeth.HackMyTeeth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mgmsec.HackMyTeeth.HackMyTeeth.model.Session;
import com.mgmsec.HackMyTeeth.HackMyTeeth.model.User;
import com.mgmsec.HackMyTeeth.HackMyTeeth.service.SessionService;
import com.mgmsec.HackMyTeeth.HackMyTeeth.service.UserService;
import com.mgmsec.HackMyTeeth.HackMyTeeth.setting.SecuritySettings;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SearchController {
	@Autowired
    SessionService sessService;

	@Autowired
	UserService userService;
	
    @Autowired
    SecuritySettings secSettings;
    
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			Cookie loginCookie = sessService.checkLoginCookie(request);
			Session sessions = sessService.findBySession(loginCookie.getValue());
			if(sessions != null) {
				modelAndView.addObject("role",sessions.getRole());
				modelAndView.addObject("username",sessions.getUsername());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		switch (secSettings.getXssProtection()) {
		case Yes:
			modelAndView.addObject("xssProtection", "Yes");				
			break;
		default:
			break;
	}
				String key=request.getParameter("keywords");
				if (key == null || key.length() == 0) {
					key = "";
			    }

				List<User> listDentist = userService.searchDentist(key);
				System.out.println(listDentist);
				for (User e: listDentist) {
					System.out.println(e.toString());
				}
				
				
				modelAndView.addObject("listDentist",listDentist);
				System.out.println("12345");
				if (key == null || key.length() == 0) {
					String all ="all dentists";
					modelAndView.addObject("keyword",all);
				}
				System.out.println("678910");
				modelAndView.addObject("keyword",key);
				modelAndView.setViewName("search");
				return modelAndView;
		}
	}

