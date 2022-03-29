package com.example.oauth2jwt.security.service;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Component
public class CorsConfigurationSource {

    private CorsConfiguration configuration;
    private UrlBasedCorsConfigurationSource source;

    public UrlBasedCorsConfigurationSource getSource() {
        configuration = new CorsConfiguration();
        configuration.addAllowedOrigin(CorsConfiguration.ALL); // (3)
        configuration.addAllowedMethod(CorsConfiguration.ALL); // (3)
        configuration.addAllowedHeader(CorsConfiguration.ALL); // (3)
        configuration.setMaxAge(3600L);
        source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // (4)
        return source;
    }
}
