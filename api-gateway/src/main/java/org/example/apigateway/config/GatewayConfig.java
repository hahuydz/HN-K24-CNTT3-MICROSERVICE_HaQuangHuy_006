package org.example.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

        @Bean
        public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
                return builder.routes()
                                .route("roomtype-service", r -> r.path("/api/roomtypes/**")
                                                .uri("lb://roomtype-service"))
                                .route("room-service", r -> r.path("/api/rooms/**")
                                                .uri("lb://room-service"))
                                .build();
        }
}
