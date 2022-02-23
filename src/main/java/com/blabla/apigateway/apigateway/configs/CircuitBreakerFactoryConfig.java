package com.blabla.apigateway.apigateway.configs;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerFactoryConfig {
    static final String POST_CIRCUIT_BREAKER = "post-query";

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> postCustomizer(
        CircuitBreakerRegistry circuitBreakerRegistry,
        TimeLimiterRegistry timeLimiterRegistry
    ) {
        return factory -> factory.configure(builder -> builder
                .circuitBreakerConfig(circuitBreakerConfig(POST_CIRCUIT_BREAKER, circuitBreakerRegistry))
                .timeLimiterConfig(timeLimiterConfig(POST_CIRCUIT_BREAKER, timeLimiterRegistry)).build(),
            POST_CIRCUIT_BREAKER);
    }

    private CircuitBreakerConfig circuitBreakerConfig(String name, CircuitBreakerRegistry circuitBreakerRegistry) {
        return circuitBreakerRegistry
            .find(name)
            .orElse(CircuitBreaker.ofDefaults(name))
            .getCircuitBreakerConfig();
    }

    private TimeLimiterConfig timeLimiterConfig(String name, TimeLimiterRegistry timeLimiterRegistry) {
        return timeLimiterRegistry
            .find(name)
            .orElse(TimeLimiter.ofDefaults())
            .getTimeLimiterConfig();
    }
}