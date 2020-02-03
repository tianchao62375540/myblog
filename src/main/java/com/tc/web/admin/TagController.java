package com.tc.web.admin;

import com.tc.po.Tag;
import com.tc.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @Auther: tianchao
 * @Date: 2020/2/2 14:54
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 跳转添加页
     * @param model
     * @return
     */
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }
    /**
     * 跳转修改
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}/input")
    public String input(@PathVariable("id") Long id, Model model){
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }
    /**
     * 添加标签
     * @param tag
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("tags")
    public String post(@Valid Tag tag, BindingResult result , RedirectAttributes attributes){
        if (validInsertOrUpdate(tag, result)) return "/admin/tags-input";
        Tag t = tagService.saveType(tag);
        if (t==null){
            attributes.addFlashAttribute("message","操作失败");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("tags/{id}")
    public String editPost(@Valid Tag tag,
                           BindingResult result ,
                           @PathVariable("id") Long id,
                           RedirectAttributes attributes){
        if (validInsertOrUpdate(tag, result)) return "/admin/tags-input";
        Tag t = tagService.update(id,tag);
        if (t==null){
            attributes.addFlashAttribute("message","修改失败");
        }else{
            attributes.addFlashAttribute("message","修改成功");
        }
        return "redirect:/admin/tags";
    }


    @GetMapping("/tags")
    public String types(@RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "rows",defaultValue = "5") Integer rows,
                        Model model){
        model.addAttribute("page", tagService.listTags(page, rows));
        return "admin/tags";
    }
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        tagService.delete(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }

    private boolean validInsertOrUpdate(@Valid Tag tag, BindingResult result) {
        Tag dbType = tagService.getTagByName(tag.getName());
        if (dbType != null){
            result.rejectValue("name", "nameError", "分类名称不能重复");
        }
        if (result.hasErrors()){
            return true;
        }
        return false;
    }
}
