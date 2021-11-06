package com.rhymesun.journey.blog.domain.tag.repository.persistence;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rhymesun.journey.blog.domain.tag.repository.facade.TagRepository;
import com.rhymesun.journey.blog.domain.tag.repository.mapper.TagDao;
import com.rhymesun.journey.blog.domain.tag.repository.po.TagPO;
import com.rhymesun.journey.blog.interfaces.dto.TagRequest;

/**
 * The implement of TagRepository.
 *
 * @author Simon
 */
@Repository
public class TagRepositoryImpl implements TagRepository {

    private static final String IS_GROUP = "is_group";
    private static final String GROUP_ID = "group_id";
    private static final String CATEGORY_ID = "category_id";

    private final TagDao tagDao;

    @Autowired
    public TagRepositoryImpl(final TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public void insert(final TagPO tagPO) {
        tagDao.insert(tagPO);
    }

    @Override
    public void update(final TagPO tagPO) {
        tagDao.updateById(tagPO);
    }

    @Override
    public List<TagPO> findByParams(final TagRequest tagRequest) {
        return tagDao.selectList(buildQueryWrapper(tagRequest));
    }

    private QueryWrapper<TagPO> buildQueryWrapper(final TagRequest tagRequest) {
        final QueryWrapper<TagPO> queryWrapper = new QueryWrapper<>();
        if (tagRequest.isOnlyGroup()) {
            queryWrapper.eq(IS_GROUP, 1);
        }
        final String groupId = tagRequest.getGroupId();
        if (StringUtils.isNotEmpty(groupId)) {
            queryWrapper.eq(GROUP_ID, groupId);
        }
        queryWrapper.orderByDesc(IS_GROUP);
        queryWrapper.orderByAsc(CATEGORY_ID);
        return queryWrapper;
    }

    @Override
    public TagPO findById(final String id) {
        return tagDao.selectById(id);
    }
}
