package com.rhymesun.journey.blog.interfaces.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * PostCountDTO.
 *
 * @author Simon
 */
@Data
public class PostCountDTO {

    private Integer year;

    private final List<Integer> monthCounts;

    public PostCountDTO() {
        this.monthCounts = new ArrayList<>(12);
        initMonthCounts(monthCounts);
    }

    public PostCountDTO(final Integer year) {
        this.year = year;
        this.monthCounts = new ArrayList<>(12);
        initMonthCounts(monthCounts);
    }

    private void initMonthCounts(final List<Integer> monthCounts) {
        for (int i = 0; i < 12; i++) {
            monthCounts.add(0);
        }
    }

    public void addMonthCount(final int month) {
        final int count = monthCounts.get(month) + 1;
        monthCounts.set(month, count);
    }
}
