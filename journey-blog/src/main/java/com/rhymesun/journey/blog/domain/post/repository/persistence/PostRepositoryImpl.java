package com.rhymesun.journey.blog.domain.post.repository.persistence;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rhymesun.journey.blog.domain.post.repository.facade.PostRepository;
import com.rhymesun.journey.blog.domain.post.repository.mapper.PostDao;
import com.rhymesun.journey.blog.domain.post.repository.po.PostPO;
import com.rhymesun.journey.blog.infrastructure.page.Page;
import com.rhymesun.journey.blog.interfaces.dto.PostRequest;

/**
 * The implement of PostRepository.
 *
 * @author Simon
 */
@Repository
public class PostRepositoryImpl implements PostRepository {

    private static final String ID = "id";
    private static final String CREATE_TIME = "create_time";
    private final Predicate<TableFieldInfo> ignorePostContent = fieldInfo ->
            !Objects.equals(fieldInfo.getColumn(), "content");

    private final PostDao postDao;

    @Autowired
    public PostRepositoryImpl(final PostDao postDao) {
        this.postDao = postDao;
    }

    public void insert(final PostPO postPO) {
        postDao.insert(postPO);
    }

    @Override
    public void update(final PostPO postPO) {
        postDao.updateById(postPO);
    }

    public PostPO findById(final String id) {
        return postDao.selectById(id);
    }

    @Override
    public Page<PostPO> page(final PostRequest request) {
        final QueryWrapper<PostPO> queryWrapper = buildQuery(request);
        final IPage<PostPO> pageRequest = new PageDTO<>(request.getPage(), request.getPageSize());
        final IPage<PostPO> result = postDao.selectPage(pageRequest, queryWrapper);

        return buildPage((int) result.getCurrent(), (int) result.getSize(), (int) result.getTotal(),
                result.getRecords());
    }

    private QueryWrapper<PostPO> buildQuery(final PostRequest request) {
        final QueryWrapper<PostPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(CREATE_TIME);
        final String title = request.getTitle();
        if (StringUtils.isNotEmpty(title)) {
            queryWrapper.like("title", title);
        }
        final String categoryId = request.getCategoryId();
        if (StringUtils.isNotEmpty(categoryId)) {
            queryWrapper.eq("category_id", categoryId);
        }
        final String tagId = request.getTagId();
        if (StringUtils.isNotEmpty(tagId)) {
            queryWrapper.like("tag_ids", tagId);
        }
        if (!request.isNeedContent()) {
            queryWrapper.select(PostPO.class, ignorePostContent);
        }
        final String year = request.getYear();
        final String month = request.getMonth();
        if (StringUtils.isNotEmpty(year)) {
            if (StringUtils.isEmpty(month)) {
                queryWrapper.eq("year(create_time)", year);
            } else {
                queryWrapper.eq("extract(year_month from create_time)", year + month);
            }
        }
        return queryWrapper;
    }

    private Page<PostPO> buildPage(final int page, final int pageSize, final int total, final List<PostPO> data) {
        return Page.createPage(page, pageSize, total, data);
    }

    @Override
    public List<PostPO> findAllCreateTime() {
        final QueryWrapper<PostPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(ID, CREATE_TIME);
        return postDao.selectList(queryWrapper);
    }
}
