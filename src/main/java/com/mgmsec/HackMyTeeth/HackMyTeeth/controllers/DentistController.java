package com.mgmsec.HackMyTeeth.HackMyTeeth.controllers;

import com.mgmsec.HackMyTeeth.HackMyTeeth.model.Appointment;
import com.mgmsec.HackMyTeeth.HackMyTeeth.model.Session;
import com.mgmsec.HackMyTeeth.HackMyTeeth.model.User;
import com.mgmsec.HackMyTeeth.HackMyTeeth.service.AppointmentService;
import com.mgmsec.HackMyTeeth.HackMyTeeth.service.SessionService;
import com.mgmsec.HackMyTeeth.HackMyTeeth.service.UserService;
import com.mgmsec.HackMyTeeth.HackMyTeeth.service.XssService;
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

    @Autowired
    AppointmentService appointmentService;

//    @Autowired
//    priate Environment environment;

    @Autowired
    SecuritySettings secSettings;

    @Value("${security.SQLI}")
    private Boolean sqlinjection;
    @Autowired
    XssService xssService;

    @RequestMapping(value = "/dentist", method = RequestMethod.GET)
    public ModelAndView dentistPage(HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Cookie loginCookie = sessService.checkLoginCookie(request);
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
    @RequestMapping(value = "/dentist", method = RequestMethod.POST)
    public ModelAndView bookingDentist (HttpServletRequest request, Model model){
        ModelAndView modelAndView = new ModelAndView();
        String title = request.getParameter("title");
        String date = request.getParameter("datebook");
        String des  = request.getParameter("description");
        switch(secSettings.getXssProtection()) {
            case Yes:
                title = xssService.escapeHtml(title);
                des = xssService.escapeHtml(des);
                break;
            default:
                break;
        }
        System.out.println("--------------: " + des);
        Cookie loginCookie = sessService.checkLoginCookie(request);
        Session sessions = sessService.findBySession(loginCookie.getValue());
        int denId  = Integer.parseInt(request.getParameter("id"));
        Appointment appointment = new Appointment();
        appointment.setTitle(title);
        appointment.setTime(date);
        appointment.setDescription(des);
        appointment.setCusID((int) sessions.getUserID());
        appointment.setDenID(denId);
        boolean result = appointmentService.insertAppointment(appointment);
        System.out.println("---------------");
        System.out.println(result);
        System.out.println(appointment.toString());
        System.out.println("---------------");

        if (!result) {
            modelAndView.addObject("errormsg", "Can not booking");
            return modelAndView;
        }
        return new ModelAndView("redirect:/home");

    }

}
