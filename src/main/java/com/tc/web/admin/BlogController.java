package com.tc.web.admin;

import com.tc.po.Blog;
import com.tc.po.PageResult;
import com.tc.po.Tag;
import com.tc.po.Type;
import com.tc.service.BlogService;
import com.tc.service.TagService;
import com.tc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/1/2 19:25
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    /**
     * 跳转添加页
     * @param model
     * @return
     */
    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("blog", new Blog());
        List<Type> types = typeService.selectAll();
        model.addAttribute("types", types);
        List<Tag> tags = tagService.selectAll();
        model.addAttribute("tags", tags);
        return "admin/blogs-input";
    }
    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession httpSession, RedirectAttributes attributes){
        httpSession.getAttribute("user");
        Blog b = blogService.saveBlog(blog);
        if (b == null){
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * 列表
     * @param page
     * @param rows
     * @param blog
     * @param model
     * @return
     */
    @GetMapping("/blogs")
    public String blogs(@RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "rows",defaultValue = "5") Integer rows,
                        Blog blog,
                        Model model){
        PageResult<Blog> list = blogService.listBlog(page, rows, blog);
        List<Type> types = typeService.selectAll();
        model.addAttribute("types", types);
        model.addAttribute("page", list);
        return "admin/blogs";
    }

    /**
     * 搜索
     * @param page
     * @param rows
     * @param blog
     * @param model
     * @return
     */
    @PostMapping("/blogs/search")
    public String search(@RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "rows",defaultValue = "5") Integer rows,
                        Blog blog,
                        Model model){
        PageResult<Blog> list = blogService.listBlog(page, rows, blog);
        model.addAttribute("page", list);
        return "admin/blogs :: blogList";
    }

}
