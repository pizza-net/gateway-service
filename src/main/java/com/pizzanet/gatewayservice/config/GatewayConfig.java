package com.pizzanet.gatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;

@Configuration
public class GatewayConfig {

    /**
     * Routing dla auth-service
     * Przekierowuje /api/auth/** -> auth-service:8081/api/auth/**
     */
    @Bean
    public RouterFunction<ServerResponse> authServiceRoute() {
        return route("auth-service")
                .route(path("/api/auth/**"), http("http://auth-service:8081"))
                .build();
    }

    /**
     * Routing dla menu-service (pizza)
     * Przekierowuje /api/pizza/** -> menu-service:8081/api/pizza/**
     */
    @Bean
    public RouterFunction<ServerResponse> menuServiceRoute() {
        return route("menu-service")
                .route(path("/api/pizza/**"), http("http://menu-service:8081"))
                .build();
    }

    /**
     * Routing dla order-service
     * Przekierowuje /api/orders/** -> order-service:8082/api/orders/**
     */
    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return route("order-service")
                .route(path("/api/orders/**"), http("http://order-service:8082"))
                .build();
    }

    /**
     * Routing dla delivery-service
     * Przekierowuje /api/deliveries/** -> delivery-service:8083/api/deliveries/**
     */
    @Bean
    public RouterFunction<ServerResponse> deliveryServiceRoute() {
        return route("delivery-service")
                .route(path("/api/deliveries/**"), http("http://delivery-service:8083"))
                .build();
    }
}
