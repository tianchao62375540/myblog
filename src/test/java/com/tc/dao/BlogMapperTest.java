package com.tc.dao;

import com.tc.po.Blog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
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

    //@Test
    public void testSaveBlogs(){
        Blog blog = new Blog();
        blog.setTitle("first");
        blog.setContent("xxxx");
        blog.setFirstPicture("https://picsum.photos/id/1/800/450");
        blog.setFlag("1");
        blog.setViews(0);
        blog.setAppreciation(true);
        blog.setShareStatement(true);
        blog.setCommentabled(true);
        blog.setPublished(true);
        blog.setRecommend(true);
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blogMapper.insert(blog);
    }
    @Test
    public void testSelectListBlogForRecommend(){
        List<Blog> blogs = blogMapper.selectListBlogForRecommend(3);
        blogs.stream().forEach(System.out::println);
    }
}