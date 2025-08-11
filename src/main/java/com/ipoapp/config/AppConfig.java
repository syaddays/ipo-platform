package com.ipoapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Application configuration class
 */
@Configuration
public class AppConfig {

    /**
     * ModelMapper bean for object mapping
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
} 