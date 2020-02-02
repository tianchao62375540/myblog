package com.tc.service;

import com.github.pagehelper.Page;
import com.tc.po.Blog;
import com.tc.po.PageResult;

/**
 * @Auther: tianchao
 * @Date: 2020/2/2 17:58
 * @Description:
 */
public interface BlogService {
    /**
     * 根据id查询博客id
     * @param id
     * @return
     */
    Blog getBlog(Long id);

    /**
     * 分页查询blog
     * @param page
     * @param rows
     * @param condition
     * @return
     */
    PageResult<Blog> listBlog(Integer page, Integer rows,Blog condition);

    /**
     * 添加
     * @param blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     * 修改
     * @param id
     * @param blog
     * @return
     */
    Blog updateBlog(Long id,Blog blog);

    /**
     * 删除
     * @param id
     */
    void deleteBlog(Long id);
}
