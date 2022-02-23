package com.blabla.apigateway.apigateway.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.services")
@Data
public class BlaBlaServices {
    private String catalogQuery;
    private String catalogCommand;
}
