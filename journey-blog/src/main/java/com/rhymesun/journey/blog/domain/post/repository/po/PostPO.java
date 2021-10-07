package com.rhymesun.journey.blog.domain.post.repository.po;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * PostPO.
 *
 * @author Simon
 */
@TableName("blog_post")
@Data
public class PostPO {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String title;
    private String contentAbstract;
    private String content;
    private Date createTime;
    private Date updateTime;
    private Short isPublished;
    private Date publishTime;
    private Short isOriginal;
    private String categoryId;
    private String tagIds;
}
