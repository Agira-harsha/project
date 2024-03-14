package com.agira.project.appconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${app-jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration}")
    private String jwtExpiration;

    // Getters for the properties
    public String getJwtSecret() {
        return jwtSecret;
    }

    public String getJwtExpiration() {
        return jwtExpiration;
    }
}
