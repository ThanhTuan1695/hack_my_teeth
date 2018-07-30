package com.mgmsec.HackMyTeeth.HackMyTeeth.setting;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "google.recaptcha")
public class CapchaSetting {

    private String uri;
    private String keySite;
    private String keySecret;

    public String getKeySecret() {
        return keySecret;
    }

    public void setKeySecret(String keySecret) {
        this.keySecret = keySecret;
    }

    public String getKeySite() {
        return keySite;
    }

    public void setKeySite(String keySite) {
        this.keySite = keySite;
    }
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
