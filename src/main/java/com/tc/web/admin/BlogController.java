package com.tc.web.admin;

import com.tc.po.*;
import com.tc.service.BlogService;
import com.tc.service.TagService;
import com.tc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        setTagAndType(model);
        model.addAttribute("blog", new Blog().setType(new Type()));
        return "admin/blogs-input";
    }

    private void setTagAndType(Model model) {
        List<Type> types = typeService.selectAll();
        model.addAttribute("types", types);
        List<Tag> tags = tagService.selectAll();
        model.addAttribute("tags", tags);
    }

    /**
     * 跳转修改
     * @param model
     * @return
     */
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        setTagAndType(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }
    /**
     *新增 修改
     * @param blog
     * @param httpSession
     * @param attributes
     * @return
     */
    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession httpSession, RedirectAttributes attributes){
        String opt = blog.getId()==null?"新增":"修改";
        User user = (User) httpSession.getAttribute("user");
        blog.setUserId(user.getId());
        Blog b = blogService.saveBlog(blog);
        if (b == null){
            attributes.addFlashAttribute("message",opt+"失败");
        }else{
            attributes.addFlashAttribute("message",opt+"成功");
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

    /**
     * 删除
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }
}
