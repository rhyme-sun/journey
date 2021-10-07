package com.rhymesun.journey.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.rhymesun.journey.blog.domain.image.repository.storage.ImageStorage;
import com.rhymesun.journey.blog.infrastructure.config.StorageConfig;

/**
 * JourneyBlogApplication.
 *
 * @author Simon
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageConfig.class)
public class JourneyBlogApplication {

    public static void main(final String[] args) {
        SpringApplication.run(JourneyBlogApplication.class, args);
    }

    @Bean
    CommandLineRunner init(final ImageStorage imageStorage) {
        return args -> {
            imageStorage.init();
        };
    }
}
