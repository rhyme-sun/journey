package com.rhymesun.journey.blog.domain.tag.repository.po;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * TagPO.
 *
 * @author Simon
 */
@Data
@TableName("blog_tag")
public class TagPO {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String name;
    private Short isGroup;
    private String groupId;
    private String categoryId;
    private Date createTime;
    private Date updateTime;
}
