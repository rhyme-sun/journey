package com.rhymesun.journey.blog.domain.post.repository.facade;

import java.util.List;

import com.rhymesun.journey.blog.domain.post.repository.po.PostPO;
import com.rhymesun.journey.blog.infrastructure.page.Page;
import com.rhymesun.journey.blog.interfaces.dto.PostRequest;

/**
 * PostRepository.
 *
 * @author Simon
 */
public interface PostRepository {

    void insert(PostPO postPO);

    void update(PostPO postPO);

    PostPO findById(String id);

    Page<PostPO> page(PostRequest request);

    List<PostPO> findAllCreateTime();
}
