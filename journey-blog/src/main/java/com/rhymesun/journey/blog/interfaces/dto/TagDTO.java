package com.rhymesun.journey.blog.interfaces.dto;

import java.util.Date;

import lombok.Data;

/**
 * TagDTO.
 *
 * @author Simon
 */
@Data
public class TagDTO {

    private String tagId;
    private String tagName;
    private Boolean isGroup;
    private String groupId;
    private String categoryId;
    private Date createTime;
    private Date updateTime;
}
