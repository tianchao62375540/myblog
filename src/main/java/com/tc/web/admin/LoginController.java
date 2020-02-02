package com.tc.web.admin;

import com.tc.po.User;
import com.tc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Auther: tianchao
 * @Date: 2020/1/1 21:10
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username, password);
        if (user==null) {
            attributes.addAttribute("message","用户名和密码错误");
            return "redirect:/admin";
        }
        user.setPassword(null);
        session.setAttribute("user",user);
        return "admin/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("user");
        return "redirect:/admin";
    }
}
