package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DevCorsConfiguration implements WebMvcConfigurer {
    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("http://localhost:4200/**").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
    }*/

    @Override
    public void addCorsMappings(CorsRegistry cors) {
        // set up cors mapping
        cors.addMapping(basePath + "/**").allowedOrigins(theAllowedOrigins).allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");
            }
        };
    }
}
