package com.tc.web;

import com.tc.ex.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Auther: tianchao
 * @Date: 2020/1/1 15:06
 * @Description:
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }
}
