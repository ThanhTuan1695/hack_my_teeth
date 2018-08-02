package com.mgmsec.HackMyTeeth.HackMyTeeth.controllers;

import com.mgmsec.HackMyTeeth.HackMyTeeth.model.Session;
import com.mgmsec.HackMyTeeth.HackMyTeeth.model.User;
import com.mgmsec.HackMyTeeth.HackMyTeeth.service.SessionService;
import com.mgmsec.HackMyTeeth.HackMyTeeth.service.UserService;
import com.mgmsec.HackMyTeeth.HackMyTeeth.setting.SecuritySettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DentistController {
    @Autowired
    UserService userService;

    @Autowired
    SessionService sessService;

//    @Autowired
//    priate Environment environment;

    @Autowired
    SecuritySettings secSettings;

    @Value("${security.SQLI}")
    private Boolean sqlinjection;

    @RequestMapping(value = "/dentist", method = RequestMethod.GET)
    public ModelAndView dentistPage(HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Cookie loginCookie = sessService.checkLoginCookie(request);
        System.out.println("hfhgf      "+secSettings.getSqli());
        if(loginCookie != null) {
            System.out.println("Login Cookie is: in dentist " +loginCookie.getValue());

            Session sessions = sessService.findBySession(loginCookie.getValue());
            if (sessions != null) {
//                String slqi = environment.getProperty("security.SQLI");
                String id = request.getParameter("id");
                if (id == null) {
                    return new ModelAndView("redirect:/home");
                }
                User dentist = userService.getDentistById(id, secSettings.getSqli());
                System.out.println(userService.getDentistById(id, secSettings.getSqli()));
                modelAndView.addObject("dentist", dentist);
                modelAndView.addObject("role",sessions.getRole());
                modelAndView.addObject("username",sessions.getUsername());
                modelAndView.addObject("userID",sessions.getUserID());
                modelAndView.addObject("denID",id);
                modelAndView.setViewName("dentist");
            }
            else {
                return new ModelAndView("redirect: /login");
            }
        }
        else {
            return new ModelAndView("redirect: /login");
        }

        return modelAndView;
    }

}
