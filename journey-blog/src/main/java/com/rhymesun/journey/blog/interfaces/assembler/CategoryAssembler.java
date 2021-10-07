package com.rhymesun.journey.blog.interfaces.assembler;

import com.rhymesun.journey.blog.domain.category.entity.Category;
import com.rhymesun.journey.blog.interfaces.dto.CategoryDTO;

/**
 * Category assembler.
 *
 * @author Simon
 */
public class CategoryAssembler {

    public static CategoryDTO toDTO(final Category from) {
        final CategoryDTO to = new CategoryDTO();
        to.setCategoryId(from.getId());
        to.setCategoryName(from.getName());
        to.setDescription(from.getDescription());
        to.setCreateTime(from.getCreateTime());
        to.setUpdateTime(from.getUpdateTime());
        return to;
    }

    public static Category toDO(final CategoryDTO from) {
        final Category to = new Category();
        to.setId(from.getCategoryId());
        to.setName(from.getCategoryName());
        to.setDescription(from.getDescription());
        to.setCreateTime(from.getCreateTime());
        to.setUpdateTime(from.getUpdateTime());
        return to;
    }
}
