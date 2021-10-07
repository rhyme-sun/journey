package com.rhymesun.journey.blog.domain.image.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rhymesun.journey.blog.domain.image.repository.storage.ImageStorage;
import com.rhymesun.journey.blog.infrastructure.config.StorageConfig;

/**
 * ImageDomainService.
 *
 * @author Simon
 */
@Service
public class ImageDomainService {

    private final ImageStorage imageStorage;
    private final StorageConfig config;

    @Autowired
    public ImageDomainService(@Qualifier("fileSystemStorage") final ImageStorage imageStorage,
                              final StorageConfig config) {
        this.imageStorage = imageStorage;
        this.config = config;
    }

    public List<String> batchStore(final List<MultipartFile> imageFiles) {
        if (CollectionUtils.isEmpty(imageFiles)) {
            return Collections.emptyList();
        }
        final List<String> imageOnlineUrls = new ArrayList<>(imageFiles.size());
        for (MultipartFile imageFile : imageFiles) {
            final String imageOnlineUrl = store(imageFile);
            imageOnlineUrls.add(imageOnlineUrl);
        }
        return imageOnlineUrls;
    }

    public String store(final MultipartFile file) {
        imageStorage.store(file);
        return config.getOnlinePrefix() + file.getOriginalFilename();
    }
}
