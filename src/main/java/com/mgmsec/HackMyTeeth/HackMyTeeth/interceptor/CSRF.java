package com.mgmsec.HackMyTeeth.HackMyTeeth.interceptor;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mgmsec.HackMyTeeth.HackMyTeeth.service.PasswordService;
import com.mgmsec.HackMyTeeth.HackMyTeeth.service.UserService;
import com.mgmsec.HackMyTeeth.HackMyTeeth.setting.SecurityEnum;
import com.mgmsec.HackMyTeeth.HackMyTeeth.setting.SecuritySettings;


public class CSRF implements HandlerInterceptor{
	@Autowired
	private PasswordService passwordService;
	@Autowired
	private SecuritySettings securitySetting;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (securitySetting.getCsrfProtection().equals(SecurityEnum.CsrfProtection.Token)) {
			if ("GET".equals(request.getMethod())) { 
				HttpSession httpSession = request.getSession();

				String _csrf = (String) httpSession.getAttribute("_csrf");
		
				// Create csrf token
				if (_csrf == null || _csrf.isEmpty()) {
					_csrf = passwordService.getRandomString(16);
					
					httpSession.setAttribute("_csrf", _csrf);
	
				}
				System.out.println(_csrf);
				request.setAttribute("_csrfToken", _csrf);
	
			}
			return true;
		}
		return true;
	}
}