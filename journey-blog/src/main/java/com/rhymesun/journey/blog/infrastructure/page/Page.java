package com.rhymesun.journey.blog.infrastructure.page;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Page object.
 *
 * @author Simon
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T>  {

    private int page;
    private int pageSize;
    private int total;

    private List<T> data;

    public static <T> Page<T> createPage(final int page, final int pageSize, final int total, final List<T> data) {
        return new Page<>(page, pageSize, total, data);
    }
}
