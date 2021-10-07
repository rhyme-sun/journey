package com.rhymesun.journey.blog.domain.category.repository.po;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * CategoryPO.
 *
 * @author Simon
 */
@Data
@TableName("blog_category")
public class CategoryPO {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String name;
    private String description;
    private Date createTime;
    private Date updateTime;
}
