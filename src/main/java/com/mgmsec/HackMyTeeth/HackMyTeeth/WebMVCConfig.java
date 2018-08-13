package com.mgmsec.HackMyTeeth.HackMyTeeth;

import com.mgmsec.HackMyTeeth.HackMyTeeth.setting.SecurityEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mgmsec.HackMyTeeth.HackMyTeeth.interceptor.CSRF;
import com.mgmsec.HackMyTeeth.HackMyTeeth.interceptor.CSRFProtection;
import com.mgmsec.HackMyTeeth.HackMyTeeth.setting.SecuritySettings;
@Configuration
public class WebMVCConfig implements WebMvcConfigurer{
	
	@Autowired
	SecuritySettings securitySetting;
	
	@Bean
	public CSRF getCSRF() {
		return new CSRF();
	}

	@Bean
	public CSRFProtection getCSRFProtection() {
		return new CSRFProtection();
	}

	@Override	
	public void addInterceptors(InterceptorRegistry registry) {
		
			// Register CSRF interceptor to generate CSRF token
			registry.addInterceptor(getCSRF()).addPathPatterns("/password","/changePassword");
			// Register CSRF prevention interceptor to check CSRF token
			registry.addInterceptor(getCSRFProtection()).addPathPatterns("/changePassword");
		
	}
}
