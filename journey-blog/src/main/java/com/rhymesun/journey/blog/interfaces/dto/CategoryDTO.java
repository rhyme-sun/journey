package com.rhymesun.journey.blog.interfaces.dto;

import java.util.Date;

import lombok.Data;

/**
 * CategoryDTO.
 *
 * @author Simon
 */
@Data
public class CategoryDTO {

    private String categoryId;
    private String categoryName;
    private String description;
    private Date createTime;
    private Date updateTime;
}
