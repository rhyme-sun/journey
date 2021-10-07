package com.rhymesun.journey.blog.domain.post.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.rhymesun.journey.blog.infrastructure.util.RegexUtils;

/**
 * Post entity.
 *
 * @author Simon
 */
@Data
public class Post {

    private String id;
    private String title;
    private String contentAbstract;
    private String content;
    private Date createTime;
    private Date updateTime;
    private Boolean isPublished;
    private Date publishTime;
    private Boolean isOriginal;
    private String category;
    private List<String> tags;

    public void setCurrentTime() {
        final Date currentTime = new Date();
        updateTime = currentTime;
        if (Objects.isNull(id)) {
            createTime = currentTime;
        }
        if (isPublished) {
            publishTime = currentTime;
        }
    }

    public Integer getCreateYear() {
        final Calendar instance = Calendar.getInstance();
        instance.setTime(createTime);
        return instance.get(Calendar.YEAR);
    }

    public Integer getCreateMonth() {
        final Calendar instance = Calendar.getInstance();
        instance.setTime(createTime);
        return instance.get(Calendar.MONTH);
    }

    public void setContentByBlogFile(final MultipartFile blogFile) {
        if (Objects.isNull(blogFile)) {
            throw new RuntimeException("上传博客文件为空");
        }
        try (Reader reader = new InputStreamReader(blogFile.getInputStream(), "utf-8");
             BufferedReader buffer = new BufferedReader(reader)) {
            String line;
            String content = "";
            while ((line = buffer.readLine()) != null) {
                content += line + System.lineSeparator();
            }
            this.setContent(content);
        } catch (IOException e) {
            throw new RuntimeException("无法读取上传博客文件的内容", e);
        }
    }

    public void replaceImageUrlOfContent(final List<String> imageOnlineUrls) {
        if (Objects.isNull(content) || CollectionUtils.isEmpty(imageOnlineUrls)) {
            return;
        }
        final List<String> matched = RegexUtils.matchMarkdownImageUrl(content);
        for (String match : matched) {
            final String imageUrl = StringUtils.substringBetween(match, "](", ")").trim();
            final boolean isOnlineUrl = imageUrl.startsWith("http");
            if (isOnlineUrl) {
                continue;
            }
            for (String imageOnlineUrl : imageOnlineUrls) {
                final String[] parts = imageOnlineUrl.split("/");
                if (imageUrl.endsWith(parts[parts.length - 1])) {
                    content = content.replace(imageUrl, imageOnlineUrl);
                }
            }
        }
    }
}
