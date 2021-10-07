package com.rhymesun.journey.blog.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rhymesun.journey.blog.domain.post.entity.Post;
import com.rhymesun.journey.blog.domain.post.service.PostDomainService;
import com.rhymesun.journey.blog.domain.image.service.ImageDomainService;
import com.rhymesun.journey.blog.infrastructure.page.Page;
import com.rhymesun.journey.blog.interfaces.dto.PostRequest;

/**
 * Post application service.
 *
 * @author Simon
 */
@Service
public class PostApplicationService {

    private final PostDomainService postDomainService;
    private final ImageDomainService imageDomainService;

    @Autowired
    public PostApplicationService(final PostDomainService postDomainService,
                                  final ImageDomainService imageDomainService) {
        this.postDomainService = postDomainService;
        this.imageDomainService = imageDomainService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(final Post post, final List<MultipartFile> imageFiles) {
        final List<String> imageOnlineUrls = imageDomainService.batchStore(imageFiles);
        postDomainService.create(post, imageOnlineUrls);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(final Post post, final MultipartFile blogFile, final List<MultipartFile> imageFiles) {
        final List<String> imageOnlineUrls = imageDomainService.batchStore(imageFiles);
        postDomainService.create(post, blogFile, imageOnlineUrls);
    }

    public void update(final Post post) {
        postDomainService.update(post);
    }

    public Post findById(final String blogId) {
        return postDomainService.findById(blogId);
    }

    public Page<Post> page(final PostRequest request) {
        return postDomainService.page(request);
    }

    public List<Post> findAllCreateTime() {
        return postDomainService.findAllCreateTime();
    }
}
