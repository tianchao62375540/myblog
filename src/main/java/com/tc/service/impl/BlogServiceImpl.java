package com.tc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tc.dao.BlogMapper;
import com.tc.dao.BlogTagsMapper;
import com.tc.dao.TagMapper;
import com.tc.dao.TypeMapper;
import com.tc.ex.NotFoundException;
import com.tc.po.*;
import com.tc.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: tianchao
 * @Date: 2020/2/2 18:02
 * @Description:
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogTagsMapper blogTagsMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TypeMapper typeMapper;
    /**
     * 根据id查询博客id
     *
     * @param id
     * @return
     */
    @Override
    public Blog getBlog(Long id) {
        Blog blog = blogMapper.selectByPrimaryKey(id);
        Example blogTagsExample = new Example(BlogTags.class);
        blogTagsExample.createCriteria().andEqualTo("blogsId", id);
        List<BlogTags> blogTags = blogTagsMapper.selectByExample(blogTagsExample);
        List<Long> tagids = blogTags.stream().map(BlogTags::getTagsId).collect(Collectors.toList());
        List<Tag> tags = tagMapper.selectByIdList(tagids);
        blog.setTags(tags);
        blog.setTagIds(StringUtils.collectionToDelimitedString(tags, ","));
        Long typeId = blog.getTypeId();
        Type type = typeMapper.selectByPrimaryKey(typeId);
        blog.setType(type);
        return blog;
    }

    /**
     * 分页查询blog
     *
     * @param page
     * @param rows
     * @param condition
     * @return
     */
    @Override
    public PageResult<Blog> listBlog(Integer page, Integer rows, Blog condition) {
        PageHelper.startPage(page, rows);
        List<Blog> blogs =  blogMapper.listBlog(condition);
        PageInfo<Blog> result = new PageInfo<>(blogs);
        return PageResult.builder(Blog.class).build(result);
    }

    /**
     * 添加 或 修改
     *
     * @param blog
     * @return
     */
    @Override
    @Transactional
    public Blog saveBlog(Blog blog) {
        if (blog.getId()==null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
            blog.setTypeId(blog.getType().getId());
            blogMapper.insertSelective(blog);
            String tagIds = blog.getTagIds();
            for (String s : tagIds.split(",")) {
                blogTagsMapper.insert(new BlogTags(blog.getId(),Long.valueOf(s)));
            }
        }else{
            //修改博客
            blog.setUpdateTime(new Date());
            blogMapper.updateByPrimaryKeySelective(blog);
            //查出原来的关联关系
            blogTagsMapper.delete(new BlogTags().setBlogsId(blog.getId()));
            String tagIds = blog.getTagIds();
            for (String s : tagIds.split(",")) {
                blogTagsMapper.insert(new BlogTags(blog.getId(),Long.valueOf(s)));
            }
        }
        return blog;
    }

    /**
     * 修改
     *
     * @param id
     * @param blog
     * @return
     */
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog dbBlog = blogMapper.selectByPrimaryKey(id);
        if (dbBlog == null){
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog, dbBlog);
        blogMapper.updateByPrimaryKey(dbBlog);
        return dbBlog;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    @Transactional
    public void deleteBlog(Long id) {
        blogMapper.deleteByPrimaryKey(id);
        blogTagsMapper.delete(new BlogTags().setBlogsId(id));
    }

    /**
     * 推荐的博客
     *
     * @param size
     * @return
     */
    @Override
    public List<Blog> listBlogForRecommend(Integer size) {
        List<Blog> blogs = blogMapper.selectListBlogForRecommend(size);
        return blogs;
    }
}
