package com.rhymesun.journey.blog.domain.category.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import com.rhymesun.journey.blog.domain.category.repository.po.CategoryPO;

/**
 * CategoryDao.
 *
 * @author Simon
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryPO> {

}
