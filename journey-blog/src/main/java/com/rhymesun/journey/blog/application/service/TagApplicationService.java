package com.rhymesun.journey.blog.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymesun.journey.blog.domain.tag.entity.Tag;
import com.rhymesun.journey.blog.domain.tag.service.TagDomainService;
import com.rhymesun.journey.blog.interfaces.dto.TagRequest;

/**
 * Tag application service.
 *
 * @author Simon
 */
@Service
public class TagApplicationService {

    private final TagDomainService tagDomainService;

    @Autowired
    public TagApplicationService(final TagDomainService tagDomainService) {
        this.tagDomainService = tagDomainService;
    }

    public void create(final Tag tag) {
        tagDomainService.create(tag);
    }

    public void update(final Tag tag) {
        tagDomainService.update(tag);
    }

    public List<Tag> findByParams(final TagRequest tagRequest) {
        return tagDomainService.findByParams(tagRequest);
    }
}
