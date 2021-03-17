package com.example.genderGuess.Configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourcePathResolverFactory {

    @Bean(name = "default")
    @ConfigurationProperties(prefix = "resource")
    public ResourcePathResolver resourcePathResolver(){
        return new ResourcePathResolver();
    }
}
