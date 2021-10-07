package com.rhymesun.journey.blog.domain.post.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rhymesun.journey.blog.domain.post.entity.Post;
import com.rhymesun.journey.blog.domain.post.repository.facade.PostRepository;
import com.rhymesun.journey.blog.domain.post.repository.po.PostPO;
import com.rhymesun.journey.blog.infrastructure.page.Page;
import com.rhymesun.journey.blog.interfaces.dto.PostRequest;

/**
 * Post domain service.
 *
 * @author Simon
 */
@Service
@Slf4j
public class PostDomainService {

    private final PostFactory postFactory;
    private final PostRepository postRepository;

    public PostDomainService(final PostFactory postFactory,
                             final PostRepository postRepository) {
        this.postRepository = postRepository;
        this.postFactory = postFactory;
    }

    public void create(final Post post, final List<String> imageOnlineUrls) {
        post.setCurrentTime();
        final PostPO postPO = postFactory.createBlogPO(post);
        postRepository.insert(postPO);
    }

    public void create(final Post post, final MultipartFile blogFile, final List<String> imageOnlineUrls) {
        post.setCurrentTime();
        post.setContentByBlogFile(blogFile);
        post.replaceImageUrlOfContent(imageOnlineUrls);
        final PostPO postPO = postFactory.createBlogPO(post);
        postRepository.insert(postPO);
    }

    public void update(final Post post) {
        final PostPO postPO = postFactory.createBlogPO(post);
        postRepository.insert(postPO);
    }

    public Post findById(final String blogId) {
        final PostPO postPO = postRepository.findById(blogId);
        return postFactory.createBlog(postPO);
    }

    public Page<Post> page(final PostRequest request) {
        final Page<PostPO> page = postRepository.page(request);
        return postFactory.createBlogPage(page);
    }

    public List<Post> findAllCreateTime() {
        final List<PostPO> blogs = postRepository.findAllCreateTime();
        return blogs.stream().map(postFactory::createBlog).collect(Collectors.toList());
    }
}
