package com.rhymesun.journey.blog.domain.category.repository.facade;

import java.util.List;

import com.rhymesun.journey.blog.domain.category.repository.po.CategoryPO;

/**
 * CategoryRepository.
 *
 * @author Simon
 */
public interface CategoryRepository {

    void insert(CategoryPO categoryPO);

    void update(CategoryPO categoryPO);

    List<CategoryPO> findAll();

    CategoryPO findById(String categoryId);
}
