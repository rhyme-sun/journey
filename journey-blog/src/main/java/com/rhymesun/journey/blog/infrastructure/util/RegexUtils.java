package com.rhymesun.journey.blog.infrastructure.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    private static final Pattern MARKDOWN_IMAGE_URL_PATTERN = Pattern.compile("!\\[.*\\]\\(.+\\)");

    public static List<String> matchMarkdownImageUrl(final String str) {
        return match(str, MARKDOWN_IMAGE_URL_PATTERN);
    }

    public static List<String> match(final String str, final Pattern pattern) {
        if (Objects.isNull(str)) {
            return Collections.emptyList();
        }
        final Matcher matcher = pattern.matcher(str);
        final List<String> matched = new LinkedList<>();
        while (matcher.find()) {
            matched.add(matcher.group());
        }
        return matched;
    }
}
