package com.rhymesun.journey.blog.interfaces.facade;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rhymesun.journey.blog.application.service.PostApplicationService;
import com.rhymesun.journey.blog.domain.post.entity.Post;
import com.rhymesun.journey.blog.infrastructure.page.Page;
import com.rhymesun.journey.blog.interfaces.assembler.BlogAssembler;
import com.rhymesun.journey.blog.interfaces.dto.PostCountDTO;
import com.rhymesun.journey.blog.interfaces.dto.PostDTO;
import com.rhymesun.journey.blog.interfaces.dto.PostRequest;

/**
 * Post rest api.
 *
 * @author Simon
 */
@RestController
@RequestMapping("/journey/posts")
public class PostRestApi {

    private final PostApplicationService postApplicationService;

    public PostRestApi(final PostApplicationService postApplicationService) {
        this.postApplicationService = postApplicationService;
    }

    @PostMapping
    @Secured("ROLE_admin")
    public void create(@RequestBody final PostDTO postDTO) {
        final Post post = BlogAssembler.toDO(postDTO);
        postApplicationService.create(post, null);
    }

    @PostMapping("/upload")
    @Secured("ROLE_admin")
    public void upload(final PostDTO postDTO,
                       @RequestParam("file") final MultipartFile blogFile,
                       @RequestParam("files[]") final List<MultipartFile> imageFiles) {
        final Post post = BlogAssembler.toDO(postDTO);
        postApplicationService.create(post, blogFile, imageFiles);
    }

    @PutMapping
    @Secured("ROLE_admin")
    public void update(final PostDTO postDTO) {
        final Post post = BlogAssembler.toDO(postDTO);
        postApplicationService.update(post);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public PostDTO findById(@PathVariable final String id) {
        final Post post = postApplicationService.findById(id);
        return BlogAssembler.toDTO(post);
    }

    @GetMapping
    public Page<PostDTO> page(final PostRequest request) {
        final Page<Post> page = postApplicationService.page(request);
        return BlogAssembler.toDTO(page);
    }

    @GetMapping("counts")
    public List<PostCountDTO> countByCreateTime() {
        final List<Post> posts = postApplicationService.findAllCreateTime();
        return BlogAssembler.toBlogCountDTO(posts);
    }
}
