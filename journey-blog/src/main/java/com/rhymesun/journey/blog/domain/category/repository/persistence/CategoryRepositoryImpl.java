package com.rhymesun.journey.blog.domain.category.repository.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rhymesun.journey.blog.domain.category.repository.facade.CategoryRepository;
import com.rhymesun.journey.blog.domain.category.repository.mapper.CategoryDao;
import com.rhymesun.journey.blog.domain.category.repository.po.CategoryPO;

/**
 * The implement of CategoryRepository.
 *
 * @author Simon
 */
@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryRepositoryImpl(final CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void insert(final CategoryPO categoryPO) {
        categoryDao.insert(categoryPO);
    }

    @Override
    public void update(final CategoryPO categoryPO) {
        categoryDao.updateById(categoryPO);
    }

    @Override
    public List<CategoryPO> findAll() {
        return categoryDao.selectList(null);
    }

    @Override
    public CategoryPO findById(final String categoryId) {
        return categoryDao.selectById(categoryId);
    }
}
