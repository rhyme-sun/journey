package com.rhymesun.journey.blog.domain.tag.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymesun.journey.blog.domain.tag.entity.Tag;
import com.rhymesun.journey.blog.domain.tag.repository.facade.TagRepository;
import com.rhymesun.journey.blog.domain.tag.repository.po.TagPO;
import com.rhymesun.journey.blog.interfaces.dto.TagRequest;

/**
 * Tag domain service.
 *
 * @author Simon
 */
@Service
public class TagDomainService {

    private final TagFactory tagFactory;
    private final TagRepository tagRepository;

    @Autowired
    public TagDomainService(final TagFactory tagFactory,
                            final TagRepository tagRepository) {
        this.tagFactory = tagFactory;
        this.tagRepository = tagRepository;
    }

    public void create(final Tag tag) {
        tag.setCurrentTime();
        final TagPO tagPO = tagFactory.createTagPO(tag);
        tagRepository.insert(tagPO);
    }

    public void update(final Tag tag) {
        tag.setCurrentTime();
        final TagPO tagPO = tagFactory.createTagPO(tag);
        tagRepository.insert(tagPO);
    }

    public List<Tag> findByParams(final TagRequest tagRequest) {
        final List<TagPO> tagPOs = tagRepository.findByParams(tagRequest);
        return tagPOs.stream().map(tagFactory::createTag).collect(Collectors.toList());
    }

    public Tag findById(final String tagId) {
        final TagPO tagPO = tagRepository.findById(tagId);
        return tagFactory.createTag(tagPO);
    }
}
