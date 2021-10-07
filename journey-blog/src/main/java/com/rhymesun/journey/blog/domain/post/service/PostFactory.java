package com.rhymesun.journey.blog.domain.post.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.rhymesun.journey.blog.domain.post.entity.Post;
import com.rhymesun.journey.blog.domain.post.repository.po.PostPO;
import com.rhymesun.journey.blog.infrastructure.page.Page;
import com.rhymesun.journey.blog.infrastructure.util.UUIDGenerator;

/**
 * Post factory.
 *
 * @author Simon
 */
@Service
public class PostFactory {

    private static final String TAG_SEPARATOR = ",";

    public PostPO createBlogPO(final Post from) {
        final PostPO to = new PostPO();
        to.setId(UUIDGenerator.generate());
        to.setTitle(from.getTitle());
        to.setContentAbstract(from.getContentAbstract());
        to.setContent(from.getContent());
        to.setCreateTime(from.getCreateTime());
        to.setUpdateTime(from.getUpdateTime());
        to.setIsPublished(toShort(from.getIsPublished()));
        to.setPublishTime(from.getPublishTime());
        to.setIsOriginal(toShort(from.getIsOriginal()));

        to.setCategoryId(from.getCategory());
        final List<String> tags = from.getTags();
        if (CollectionUtils.isNotEmpty(tags)) {
            final String tagIds = StringUtils.join(tags, TAG_SEPARATOR);
            to.setTagIds(tagIds);
        }
        return to;
    }

    public Post createBlog(final PostPO from) {
        final Post to = new Post();
        to.setId(from.getId());
        to.setTitle(from.getTitle());
        to.setContentAbstract(from.getContentAbstract());
        to.setContent(from.getContent());
        to.setCreateTime(from.getCreateTime());
        to.setUpdateTime(from.getUpdateTime());
        to.setIsPublished(toBoolean(from.getIsPublished()));
        to.setPublishTime(from.getPublishTime());
        to.setIsOriginal(toBoolean(from.getIsOriginal()));

        to.setCategory(from.getCategoryId());

        final String tagIds = from.getTagIds();
        if (StringUtils.isNotEmpty(tagIds)) {
            final List<String> tags = Arrays.asList(tagIds.split(TAG_SEPARATOR));
            to.setTags(tags);
        }
        return to;
    }

    public Page<Post> createBlogPage(final Page<PostPO> from) {
        final List<Post> posts = from.getData().stream().map(this::createBlog).collect(Collectors.toList());
        return Page.createPage(from.getPage(), from.getPageSize(), from.getTotal(), posts);
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
