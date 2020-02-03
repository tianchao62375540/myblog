package com.tc.web;

import com.tc.ex.NotFoundException;
import com.tc.po.Blog;
import com.tc.service.BlogService;
import com.tc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("/")
    public String index(@RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "rows",defaultValue = "5") Integer rows,
                        Model model){
        model.addAttribute("page", blogService.listBlog(page, rows, null));
        typeService.listTypeTop(6);

        return "index";
    }
    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }
}
