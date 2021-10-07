package com.rhymesun.journey.blog.interfaces.assembler;

import com.rhymesun.journey.blog.domain.tag.entity.Tag;
import com.rhymesun.journey.blog.interfaces.dto.TagDTO;

/**
 * Tag assembler.
 *
 * @author Simon
 */
public class TagAssembler {

    public static TagDTO toDTO(final Tag from) {
        final TagDTO to = new TagDTO();
        to.setTagId(from.getId());
        to.setTagName(from.getName());
        to.setIsGroup(from.getIsGroup());
        to.setGroupId(from.getGroupId());
        to.setCategoryId(from.getCategoryId());
        to.setUpdateTime(from.getUpdateTime());
        to.setCreateTime(from.getUpdateTime());
        return to;
    }

    public static Tag toDO(final TagDTO from) {
        final Tag to = new Tag();
        to.setId(from.getTagId());
        to.setName(from.getTagName());
        to.setIsGroup(from.getIsGroup());
        to.setGroupId(from.getGroupId());
        to.setCategoryId(from.getCategoryId());
        to.setUpdateTime(from.getUpdateTime());
        to.setCreateTime(from.getUpdateTime());
        return to;
    }
}
