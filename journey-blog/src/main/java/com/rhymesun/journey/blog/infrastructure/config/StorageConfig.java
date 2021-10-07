package com.rhymesun.journey.blog.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * StorageConfig.
 *
 * @author Simon
 */
@ConfigurationProperties("storage")
@Data
public class StorageConfig {

    private String location = "upload-dir";
    private String type = "file-system";
    private String onlinePrefix;
}
