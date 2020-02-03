package com.tc.web;

import com.tc.ex.NotFoundException;
import com.tc.po.Blog;
import com.tc.po.Tag;
import com.tc.po.Type;
import com.tc.service.BlogService;
import com.tc.service.TagService;
import com.tc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/1/1 15:06
 * @Description:
 */
@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;
    @GetMapping("/")
    public String index(@RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "rows",defaultValue = "5") Integer rows,
                        Model model){
        model.addAttribute("page", blogService.listBlog(page, rows, null));
        List<Type> types = typeService.listTypeTop(6);
        model.addAttribute("types", types);
        List<Tag> tags = tagService.selectTagTop(10);
        model.addAttribute("tags", tags);
        List<Blog> recommendBlogs = blogService.listBlogForRecommend(10);
        model.addAttribute("recommendBlogs", recommendBlogs);
        return "index";
    }
    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }
}
