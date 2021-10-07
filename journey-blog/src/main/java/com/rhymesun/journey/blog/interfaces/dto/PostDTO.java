package com.rhymesun.journey.blog.interfaces.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * Post DTO.
 *
 * @author Simon
 */
@Data
public class PostDTO {

    private String postId;
    private String title;
    private String contentAbstract;
    private String content;
    private Date createTime;
    private Date updateTime;
    private Boolean isPublished;
    private Date publishTime;
    private Boolean isOriginal;
    private String categoryId;
    private List<String> tagIds;
}
