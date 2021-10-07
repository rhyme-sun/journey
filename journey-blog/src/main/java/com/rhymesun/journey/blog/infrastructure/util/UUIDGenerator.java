package com.rhymesun.journey.blog.infrastructure.util;

import java.util.UUID;

/**
 * UUID generator.
 *
 * @author Simon
 */
public class UUIDGenerator {

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
