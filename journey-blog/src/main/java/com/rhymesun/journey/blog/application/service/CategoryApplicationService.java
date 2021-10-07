package com.rhymesun.journey.blog.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymesun.journey.blog.domain.category.entity.Category;
import com.rhymesun.journey.blog.domain.category.service.CategoryDomainService;

/**
 * Category application service.
 *
 * @author Simon
 */
@Service
public class CategoryApplicationService {

    private final CategoryDomainService categoryDomainService;

    @Autowired
    public CategoryApplicationService(final CategoryDomainService categoryDomainService) {
        this.categoryDomainService = categoryDomainService;
    }

    public void create(final Category category) {
        categoryDomainService.create(category);
    }

    public void update(final Category category) {
        categoryDomainService.update(category);
    }

    public List<Category> findAll() {
        return categoryDomainService.findAll();
    }
}
