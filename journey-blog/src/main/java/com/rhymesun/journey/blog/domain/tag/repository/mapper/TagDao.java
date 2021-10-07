package com.rhymesun.journey.blog.domain.tag.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import com.rhymesun.journey.blog.domain.tag.repository.po.TagPO;

/**
 * TagDao.
 *
 * @author ykthree
 */
@Mapper
public interface TagDao extends BaseMapper<TagPO> {
}
