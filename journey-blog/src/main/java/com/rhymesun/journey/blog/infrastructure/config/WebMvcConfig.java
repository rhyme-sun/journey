package com.rhymesun.journey.blog.infrastructure.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfig.
 *
 * @author Simon
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String ASTERISK = "*";
    private static final String PATH_PATTERN = "/**";

    @Value("${storage.location}")
    private String location;

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping(PATH_PATTERN)
                .allowCredentials(true)
                .allowedOriginPatterns(ASTERISK)
                .allowedMethods(ASTERISK)
                .allowedHeaders(ASTERISK)
                .maxAge(Duration.ofHours(1).getSeconds());
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler(PATH_PATTERN).addResourceLocations("file:" + location);
    }
}
