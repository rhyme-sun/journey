package com.rhymesun.journey.blog.infrastructure.config;

import java.time.Duration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfig.
 *
 * @author Simon
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String ASTERISK = "*";

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("http://localhost:[8080]")
                .allowedMethods(ASTERISK)
                .allowedHeaders(ASTERISK)
                .maxAge(Duration.ofHours(1).getSeconds());
    }
}
