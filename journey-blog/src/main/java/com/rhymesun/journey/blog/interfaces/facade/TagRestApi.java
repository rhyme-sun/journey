package com.rhymesun.journey.blog.interfaces.facade;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhymesun.journey.blog.application.service.TagApplicationService;
import com.rhymesun.journey.blog.domain.tag.entity.Tag;
import com.rhymesun.journey.blog.interfaces.assembler.TagAssembler;
import com.rhymesun.journey.blog.interfaces.dto.TagDTO;
import com.rhymesun.journey.blog.interfaces.dto.TagRequest;

/**
 * Tag rest api.
 *
 * @author Simon
 */
@RestController
@RequestMapping("/journey/tags")
public class TagRestApi {

    private final TagApplicationService tagApplicationService;

    public TagRestApi(final TagApplicationService tagApplicationService) {
        this.tagApplicationService = tagApplicationService;
    }

    @PostMapping
    @Secured("ROLE_admin")
    public void create(@RequestBody final TagDTO tagDTO) {
        tagApplicationService.create(TagAssembler.toDO(tagDTO));
    }

    @PutMapping
    @Secured("ROLE_admin")
    public void update(@RequestBody final TagDTO tagDTO) {
        tagApplicationService.update(TagAssembler.toDO(tagDTO));
    }

    @GetMapping
    public List<TagDTO> findByParams(final TagRequest tagRequest) {
        final List<Tag> tags = tagApplicationService.findByParams(tagRequest);
        return tags.stream().map(TagAssembler::toDTO).collect(Collectors.toList());
    }
}
