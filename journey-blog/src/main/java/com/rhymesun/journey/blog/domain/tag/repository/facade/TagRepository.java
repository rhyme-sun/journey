package com.rhymesun.journey.blog.domain.tag.repository.facade;

import java.util.List;

import com.rhymesun.journey.blog.domain.tag.repository.po.TagPO;
import com.rhymesun.journey.blog.interfaces.dto.TagRequest;

/**
 * TagRepository.
 *
 * @author Simon
 */
public interface TagRepository {

    void insert(TagPO tagPO);

    void update(TagPO tagPO);

    List<TagPO> findByParams(TagRequest tagRequest);

    TagPO findById(String id);
}
