package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan
@EnableRetry
public class DemoCircuitBreakerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCircuitBreakerServiceApplication.class, args);
	}

}
