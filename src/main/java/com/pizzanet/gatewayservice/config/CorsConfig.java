package com.pizzanet.gatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Dozwolone originy (frontend)
        configuration.setAllowedOrigins(List.of(
                "http://localhost:5173",     // Vite dev server
                "http://localhost:3000",     // Alternatywny port
                "http://localhost:4173",     // Vite preview
                "http://localhost:8085",     // Frontend w Docker
                "http://pizza-frontend:80",  // Frontend container
                "http://127.0.0.1:5173",
                "http://127.0.0.1:8085"
        ));

        // Dozwolone metody HTTP
        configuration.setAllowedMethods(List.of(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS",
                "PATCH"
        ));

        // Dozwolone nagłówki
        configuration.setAllowedHeaders(List.of(
                "Authorization",
                "Content-Type",
                "Accept",
                "Origin",
                "X-Requested-With",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers"
        ));

        // Nagłówki zwracane w odpowiedzi
        configuration.setExposedHeaders(List.of(
                "Authorization",
                "Content-Type"
        ));

        // Zezwolenie na credentials (cookies, authorization headers)
        configuration.setAllowCredentials(true);

        // Maksymalny czas cache dla preflight request
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }
}
