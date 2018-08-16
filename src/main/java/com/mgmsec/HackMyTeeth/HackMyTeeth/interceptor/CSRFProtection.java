package com.mgmsec.HackMyTeeth.HackMyTeeth.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import com.mgmsec.HackMyTeeth.HackMyTeeth.interceptor.CSRF;
import com.mgmsec.HackMyTeeth.HackMyTeeth.service.PasswordService;
import com.mgmsec.HackMyTeeth.HackMyTeeth.setting.SecurityEnum;
import com.mgmsec.HackMyTeeth.HackMyTeeth.setting.SecuritySettings;
public class CSRFProtection implements HandlerInterceptor{
	@Autowired
	private SecuritySettings securitySetting;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (securitySetting.getCsrfProtection().equals(SecurityEnum.CsrfProtection.Token)) {

			// Check csrf token
			if (request.getMethod().matches("POST|PUT|PATCH|DELETE")) {
				HttpSession httpSession = request.getSession();
				String _csrf = (String) httpSession.getAttribute("_csrf");
				String _csrfParam = null;
				
					_csrfParam = request.getParameter("_csrf");
				
				System.out.println("Session:"+ httpSession + "_csrf:" + _csrf + "_csrfToken:" + _csrfParam);
				if (_csrfParam == null || _csrf.equals(_csrfParam) == false) {
					response.sendError(401);
					return false;
				}
			}
			return true;
			
		}
		return true;
	}
}
 