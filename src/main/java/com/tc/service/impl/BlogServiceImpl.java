package com.tc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tc.dao.BlogMapper;
import com.tc.ex.NotFoundException;
import com.tc.po.Blog;
import com.tc.po.PageResult;
import com.tc.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/2/2 18:02
 * @Description:
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;
    /**
     * 根据id查询博客id
     *
     * @param id
     * @return
     */
    @Override
    public Blog getBlog(Long id) {
        return blogMapper.selectByPrimaryKey(id);
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
     * 添加
     *
     * @param blog
     * @return
     */
    @Override
    public Blog saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blogMapper.insertSelective(blog);
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
    public void deleteBlog(Long id) {
        blogMapper.deleteByPrimaryKey(id);
    }
}
