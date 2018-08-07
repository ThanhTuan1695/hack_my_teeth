package com.mgmsec.HackMyTeeth.HackMyTeeth.service;

import com.mgmsec.HackMyTeeth.HackMyTeeth.setting.CapchaSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CapchaSeviceImpl implements CapchaService {
    @Autowired
    private CapchaSetting capchaSetting;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;
    @Override
    public String getReCaptchaKeySite() {
        return capchaSetting.getKeySite();
    }

    @Override
    public String getReCaptchaKeySecret() {
        return capchaSetting.getKeySecret();
    }

    @Override
    public boolean verifyResponse(String g_recaptcha_response, String ip) {
        Map<String, String> body = new HashMap<>();
        body.put("secret", capchaSetting.getKeySecret());
        body.put("response", g_recaptcha_response);
        body.put("remoteip", ip);
        ResponseEntity<Map> recaptchaResponseEntity = restTemplateBuilder.build()
                .postForEntity(capchaSetting.getUri()+"?secret={secret}&response={response}&remoteip={remoteip}",
                        body, Map.class, body);

        Map<String, Object> responseBody = recaptchaResponseEntity.getBody();
        System.out.println(responseBody);
        boolean recaptchaSucess = (Boolean)responseBody.get("success");
        return recaptchaSucess;
    }
}
