package com.breno.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/v1/cores")
                .allowedOrigins("http://localhost:5173") // Defina a origem permitida
                .allowedMethods("GET", "POST", "PUT", "DELETE");

        registry.addMapping("/v1/cadastro")
                .allowedOrigins("http://localhost:5173") // Defina a origem permitida
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}