package com.rhymesun.journey.blog.domain.category.entity;

import java.util.Date;
import java.util.Objects;

import lombok.Data;

/**
 * Post category entity.
 *
 * @author Simon
 */
@Data
public class Category {

    private String id;
    private String name;
    private String description;
    private Date createTime;
    private Date updateTime;

    public void setCurrentTime() {
        final Date currentTime = new Date();
        updateTime = currentTime;
        if (Objects.isNull(id)) {
            createTime = currentTime;
        }
    }
}
