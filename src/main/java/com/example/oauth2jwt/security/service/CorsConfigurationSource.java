package com.example.oauth2jwt.security.service;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Component
public class CorsConfigurationSource {

    private final CorsConfiguration configuration;
    private final UrlBasedCorsConfigurationSource source;

    public CorsConfigurationSource() {
        this.configuration = new CorsConfiguration();
        this.source = new UrlBasedCorsConfigurationSource();
    }

    public UrlBasedCorsConfigurationSource getSource() {
        configuration.addAllowedOrigin(CorsConfiguration.ALL);
        configuration.addAllowedMethod(CorsConfiguration.ALL);
        configuration.addAllowedHeader(CorsConfiguration.ALL);
        configuration.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
