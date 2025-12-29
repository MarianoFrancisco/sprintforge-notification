package com.sprintforge.notification.common.infrastructure.verification;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.verification")
@Getter
@Setter
public class VerificationProperties {

    private String baseUrl;
    private String endpoint;

    public String buildLink(String token) {
        return baseUrl + endpoint + "?token=" + token;
    }
}
