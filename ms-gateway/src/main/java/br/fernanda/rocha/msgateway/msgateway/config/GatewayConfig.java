package br.fernanda.rocha.msgateway.msgateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("ms-proposta", r -> r.path("/propostas/**")
                        .uri("lb://ms-proposta"))
                .route("ms-funcionarios", r -> r.path("/funcionarios/**")
                        .uri("lb://ms-funcionarios"))
                .build();
    }
}

