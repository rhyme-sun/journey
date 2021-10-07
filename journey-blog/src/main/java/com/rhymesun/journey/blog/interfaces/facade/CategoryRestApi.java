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

import com.rhymesun.journey.blog.application.service.CategoryApplicationService;
import com.rhymesun.journey.blog.domain.category.entity.Category;
import com.rhymesun.journey.blog.interfaces.assembler.CategoryAssembler;
import com.rhymesun.journey.blog.interfaces.dto.CategoryDTO;

/**
 * Category rest api.
 *
 * @author Simon
 */
@RestController
@RequestMapping("/journey/categories")
public class CategoryRestApi {

    private final CategoryApplicationService categoryApplicationService;

    public CategoryRestApi(final CategoryApplicationService categoryApplicationService) {
        this.categoryApplicationService = categoryApplicationService;
    }

    @PostMapping
    @Secured("ROLE_admin")
    public void create(@RequestBody final CategoryDTO categoryDTO) {
        categoryApplicationService.create(CategoryAssembler.toDO(categoryDTO));
    }

    @PutMapping
    @Secured("ROLE_admin")
    public void update(@RequestBody final CategoryDTO categoryDTO) {
        categoryApplicationService.update(CategoryAssembler.toDO(categoryDTO));
    }

    @GetMapping
    public List<CategoryDTO> findAll() {
        final List<Category> categories = categoryApplicationService.findAll();
        return categories.stream().map(CategoryAssembler::toDTO).collect(Collectors.toList());
    }
}
