package com.rhymesun.journey.blog.domain.category.service;

import org.springframework.stereotype.Service;

import com.rhymesun.journey.blog.domain.category.entity.Category;
import com.rhymesun.journey.blog.domain.category.repository.po.CategoryPO;

/**
 * Category factory.
 *
 * @author Simon
 */
@Service
public class CategoryFactory {

    public CategoryPO createCategoryPO(final Category from) {
        final CategoryPO to = new CategoryPO();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setDescription(from.getDescription());
        to.setCreateTime(from.getCreateTime());
        to.setUpdateTime(from.getUpdateTime());
        return to;
    }

    public Category createCategory(final CategoryPO from) {
        final Category to = new Category();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setDescription(from.getDescription());
        to.setCreateTime(from.getCreateTime());
        to.setUpdateTime(from.getUpdateTime());
        return to;
    }
}
