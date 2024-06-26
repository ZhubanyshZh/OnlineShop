package org.example.config;

import org.example.patternCompositeWithIterator.CompositeCategory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfiguration implements WebMvcConfigurer {
    @Bean
    @Scope("prototype")
    public CompositeCategory getCompositeCategory(){
        return new CompositeCategory("All categories");
    }
}
