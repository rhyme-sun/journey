package com.rhymesun.journey.blog.domain.post.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import com.rhymesun.journey.blog.domain.post.repository.po.PostPO;

/**
 * PostDao.
 *
 * @author Simon
 */
@Mapper
public interface PostDao extends BaseMapper<PostPO> {

}
