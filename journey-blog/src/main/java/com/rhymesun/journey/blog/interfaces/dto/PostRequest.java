package com.rhymesun.journey.blog.interfaces.dto;

import lombok.Data;

/**
 * PostRequest.
 *
 * @author Simon
 */
@Data
public class PostRequest {

    private int page = 1;
    private int pageSize = 10;

    private String title;
    private String categoryId;
    private String tagId;
    private boolean needContent;

    private String year;
    private String month;
}
