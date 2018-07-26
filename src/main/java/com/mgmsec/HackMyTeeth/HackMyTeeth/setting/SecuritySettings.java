package com.mgmsec.HackMyTeeth.HackMyTeeth.setting;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import com.mgmsec.HackMyTeeth.HackMyTeeth.setting.SecurityEnum.*;

@Component
@ConfigurationProperties(prefix="security")
public class SecuritySettings{
	private UseCookie useCookie;
	private PwdStorage pwdStorage;
	private CookieParam cookParam;
	private SessFix sessFix;
	private LoginError logErr;
	private ResetPassword resetPassword;
	
	public CookieParam getCookParam() {
		return cookParam;
	}
	public void setCookParam(CookieParam cookParam) {
		this.cookParam = cookParam;
	}
	public SessFix getSessFix() {
		return sessFix;
	}
	public void setSessFix(SessFix sessFix) {
		this.sessFix = sessFix;
	}
	public LoginError getLogErr() {
		return logErr;
	}
	public void setLogErr(LoginError logErr) {
		this.logErr = logErr;
	}
	public UseCookie getUseCookie() {
		return useCookie;
	}
	public void setUseCookie(UseCookie useCookie) {
		this.useCookie = useCookie;
	}
	public PwdStorage getPwdStorage() {
		return pwdStorage;
	}
	public void setPwdStorage(PwdStorage pwdStorage) {
		this.pwdStorage = pwdStorage;
	}

	public ResetPassword getResetPassword() {
		return resetPassword;
	}

	public void setResetPassword(ResetPassword resetPassword) {
		this.resetPassword = resetPassword;
	}
	
}
