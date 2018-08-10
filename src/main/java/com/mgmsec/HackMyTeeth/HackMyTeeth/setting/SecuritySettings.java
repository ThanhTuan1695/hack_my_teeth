package com.mgmsec.HackMyTeeth.HackMyTeeth.setting;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import com.mgmsec.HackMyTeeth.HackMyTeeth.setting.SecurityEnum.*;

@Component
@ConfigurationProperties(prefix = "security")
public class SecuritySettings {
    private UseCookie useCookie;
    private PwdStorage pwdStorage;
    private CookieParam cookParam;
    private SessFix sessFix;
    private LoginError logErr;
    private Boolean sqli;
    private ResetPassword resetPassword;
    private PwBruteForce pwBruteForce;
    private XssProtection xssProtection;
    private Boolean csrf;
    
	public Boolean getCsrf() {
		return csrf;
	}

	public void setCsrf(Boolean csrf) {
		this.csrf = csrf;
	}
	
    public XssProtection getXssProtection() {
		return xssProtection;
	}

	public void setXssProtection(XssProtection xssProtection) {
		this.xssProtection = xssProtection;
	}

	public void setSqli(Boolean sqli) {
        this.sqli = sqli;
    }

    public Boolean getSqli() {
        return sqli;
    }

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

    public PwBruteForce getPwBruteForce() {
        return pwBruteForce;
    }

    public void setPwBruteForce(PwBruteForce pwBruteForce) {
        this.pwBruteForce = pwBruteForce;
    }

}
