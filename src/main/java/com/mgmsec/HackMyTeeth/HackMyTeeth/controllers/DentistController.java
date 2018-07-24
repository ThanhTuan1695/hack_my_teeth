package com.mgmsec.HackMyTeeth.HackMyTeeth.controllers;

import com.mgmsec.HackMyTeeth.HackMyTeeth.model.Session;
import com.mgmsec.HackMyTeeth.HackMyTeeth.model.User;
import com.mgmsec.HackMyTeeth.HackMyTeeth.service.SessionService;
import com.mgmsec.HackMyTeeth.HackMyTeeth.service.UserService;
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

    @Autowired
    private Environment environment;

    @Value("${security.SQLI}")
    private Boolean sqlinjection;

    @RequestMapping(value = "/dentist", method = RequestMethod.GET)
    public ModelAndView dentistPage(HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Cookie loginCookie = sessService.checkLoginCookie(request);
        System.out.println(loginCookie);
        if(loginCookie != null) {
            System.out.println("Login Cookie is: " +loginCookie.getValue());

            Session sessions = sessService.findBySession(loginCookie.getValue());
            if (sessions != null) {
                String slqi = environment.getProperty("security.SQLI");
                System.out.println(slqi);
                String id = request.getParameter("id");
                if (id == null) {
                    return new ModelAndView("redirect:/home");
                }
                User dentist = userService.getDentistById(id, sqlinjection);
                System.out.println(userService.getDentistById(id, sqlinjection));
                modelAndView.addObject("dentist", dentist);
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
