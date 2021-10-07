package com.rhymesun.journey.blog.interfaces.dto;

import lombok.Data;

/**
 * TagRequest.
 *
 * @author Simon
 */
@Data
public class TagRequest {

    private boolean onlyGroup;
    private String groupId;
}
