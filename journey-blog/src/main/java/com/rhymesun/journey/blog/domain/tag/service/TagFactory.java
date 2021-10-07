package com.rhymesun.journey.blog.domain.tag.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.rhymesun.journey.blog.domain.tag.entity.Tag;
import com.rhymesun.journey.blog.domain.tag.repository.po.TagPO;

/**
 * Tag factory.
 *
 * @author Simon
 */
@Service
public class TagFactory {

    public TagPO createTagPO(final Tag from) {
        final TagPO to = new TagPO();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setIsGroup(toShort(from.getIsGroup()));
        to.setGroupId(from.getGroupId());
        to.setCategoryId(from.getCategoryId());
        to.setUpdateTime(from.getUpdateTime());
        to.setCreateTime(from.getUpdateTime());
        return to;
    }

    public Tag createTag(final TagPO from) {
        final Tag to = new Tag();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setIsGroup(toBoolean(from.getIsGroup()));
        to.setGroupId(from.getGroupId());
        to.setCategoryId(from.getCategoryId());
        to.setUpdateTime(from.getUpdateTime());
        to.setCreateTime(from.getUpdateTime());
        return to;
    }

    private boolean toBoolean(final Short shortValue) {
        return Objects.equals((short) 1, shortValue);
    }

    private Short toShort(final Boolean booleanValue) {
        if (booleanValue == null) {
            return 0;
        }
        if (booleanValue) {
            return 1;
        } else {
            return 0;
        }
    }
}
