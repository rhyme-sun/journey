package com.rhymesun.journey.blog.domain.tag.entity;

import java.util.Date;
import java.util.Objects;

import lombok.Data;

/**
 * Post tag entity.
 *
 * @author Simon
 */
@Data
public class Tag {

    private String id;
    private String name;
    private Boolean isGroup;
    private String groupId;
    private String categoryId;
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
