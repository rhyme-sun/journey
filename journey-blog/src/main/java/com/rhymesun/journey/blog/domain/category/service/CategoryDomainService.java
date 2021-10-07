package com.rhymesun.journey.blog.domain.category.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymesun.journey.blog.domain.category.entity.Category;
import com.rhymesun.journey.blog.domain.category.repository.facade.CategoryRepository;
import com.rhymesun.journey.blog.domain.category.repository.po.CategoryPO;

/**
 * Category domain service.
 *
 * @author Simon
 */
@Service
public class CategoryDomainService {

    private final CategoryFactory categoryFactory;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryDomainService(final CategoryFactory categoryFactory,
                                 final CategoryRepository categoryRepository) {
        this.categoryFactory = categoryFactory;
        this.categoryRepository = categoryRepository;
    }

    public void create(final Category category) {
        category.setCurrentTime();
        final CategoryPO categoryPO = categoryFactory.createCategoryPO(category);
        categoryRepository.insert(categoryPO);
    }

    public void update(final Category category) {
        category.setCurrentTime();
        final CategoryPO categoryPO = categoryFactory.createCategoryPO(category);
        categoryRepository.update(categoryPO);
    }

    public List<Category> findAll() {
        final List<CategoryPO> categoryPOs = categoryRepository.findAll();
        return categoryPOs.stream().map(categoryFactory::createCategory).collect(Collectors.toList());
    }

    public Category findById(final String categoryId) {
        final CategoryPO categoryPO = categoryRepository.findById(categoryId);
        return categoryFactory.createCategory(categoryPO);
    }
}
