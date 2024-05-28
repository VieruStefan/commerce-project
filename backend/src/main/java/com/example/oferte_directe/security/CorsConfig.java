package com.example.oferte_directe.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://54.243.193.76:4200")
//                .allowedMethods("GET", "POST", "DELETE", "PUT")
//                .allowedHeaders("*");
        registry.addMapping("/listings/**")
                .allowedOrigins("http://54.243.193.76:4200")
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .allowedHeaders("*");
//        registry.addMapping("/users/**")
//                .allowedOrigins("http://54.243.193.76:4200")
//                .allowedMethods("GET", "POST", "DELETE", "PUT")
//                .allowedHeaders("*");
    }
}