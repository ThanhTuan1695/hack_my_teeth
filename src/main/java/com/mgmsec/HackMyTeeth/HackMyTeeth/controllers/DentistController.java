package com.mgmsec.HackMyTeeth.HackMyTeeth.controllers;

import com.mgmsec.HackMyTeeth.HackMyTeeth.model.User;
import com.mgmsec.HackMyTeeth.HackMyTeeth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DentistController {
    @Autowired
    UserService userService;

    @Autowired
    private Environment environment;

    @Value("${security.SQLI}")
    private Boolean sqlinjection;

    @RequestMapping(value = "/dentist", method = RequestMethod.GET)
    public ModelAndView dentistPage(HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView();
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
        return modelAndView;
    }

}