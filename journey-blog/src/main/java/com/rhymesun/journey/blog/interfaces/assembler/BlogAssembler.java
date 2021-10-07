package com.rhymesun.journey.blog.interfaces.assembler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import com.rhymesun.journey.blog.domain.post.entity.Post;
import com.rhymesun.journey.blog.infrastructure.page.Page;
import com.rhymesun.journey.blog.interfaces.dto.PostCountDTO;
import com.rhymesun.journey.blog.interfaces.dto.PostDTO;

/**
 * Post assembler.
 *
 * @author Simon
 */
@Slf4j
public class BlogAssembler {

    public static PostDTO toDTO(final Post from) {
        final PostDTO to = new PostDTO();
        to.setPostId(from.getId());
        to.setTitle(from.getTitle());
        to.setContentAbstract(from.getContentAbstract());
        to.setContent(from.getContent());
        to.setCreateTime(from.getCreateTime());
        to.setUpdateTime(from.getUpdateTime());
        to.setIsPublished(from.getIsPublished());
        to.setPublishTime(from.getPublishTime());
        to.setIsOriginal(from.getIsOriginal());
        to.setCategoryId(from.getCategory());
        to.setTagIds(from.getTags());
        return to;
    }

    public static Page<PostDTO> toDTO(final Page<Post> from) {
        final List<PostDTO> data = from.getData().stream().map(BlogAssembler::toDTO).collect(Collectors.toList());
        return Page.createPage(from.getPage(), from.getPageSize(), from.getTotal(), data);
    }

    public static Post toDO(final PostDTO from) {
        final Post to = new Post();
        to.setId(from.getPostId());
        to.setTitle(from.getTitle());
        to.setContentAbstract(from.getContentAbstract());
        to.setContent(from.getContent());
        to.setCreateTime(from.getCreateTime());
        to.setUpdateTime(from.getUpdateTime());
        to.setIsPublished(from.getIsPublished());
        if (to.getIsPublished()) {
            to.setPublishTime(to.getUpdateTime());
        }
        to.setIsOriginal(from.getIsOriginal());

        to.setCategory(from.getCategoryId());
        to.setTags(from.getTagIds());
        return to;
    }

    public static List<PostCountDTO> toBlogCountDTO(final List<Post> from) {
        final Map<Integer, PostCountDTO> countMap = new LinkedHashMap<>();
        for (Post post : from) {
            final Integer createYear = post.getCreateYear();
            final PostCountDTO postCountDTO = countMap.computeIfAbsent(createYear, year -> new PostCountDTO(year));
            postCountDTO.addMonthCount(post.getCreateMonth());
        }
        return new ArrayList<>(countMap.values());
    }
}
