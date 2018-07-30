package com.mgmsec.HackMyTeeth.HackMyTeeth.service;

public interface CapchaService {

    String getReCaptchaKeySite();

    String getReCaptchaKeySecret();

    boolean verifyResponse(String g_recaptcha_response, String ip);
}
