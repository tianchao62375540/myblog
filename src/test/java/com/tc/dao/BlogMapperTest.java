package com.tc.dao;

import com.tc.po.Blog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: tianchao
 * @Date: 2020/2/2 18:47
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogMapperTest {


    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void testListBlog(){
        Blog blog = new Blog();
        blog.setRecommend(true);
        List<Blog> blogs = blogMapper.listBlog(blog);
        System.out.println(blogs);
    }
}