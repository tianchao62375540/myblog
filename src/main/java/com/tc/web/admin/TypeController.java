package com.tc.web.admin;

import com.github.pagehelper.PageInfo;
import com.tc.po.PageResult;
import com.tc.po.Type;
import com.tc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @Auther: tianchao
 * @Date: 2020/1/2 20:21
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 查询
     * @param page
     * @param rows
     * @param model
     * @return
     */
    @GetMapping("/types")
    public String types(@RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "rows",defaultValue = "5") Integer rows,
                        Model model){
        model.addAttribute("page", typeService.listType(page, rows));
        return "admin/types";
    }

    /**
     * 跳转新增页面
     * @return
     */
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type", new Type());
        return "/admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        Type type = typeService.getType(id);
        model.addAttribute("type",type);
        return "/admin/types-input";
    }

    /**
     * 添加
     * @param type
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("types")
    public String post(@Valid Type type, BindingResult result , RedirectAttributes attributes){
        if (validInsertOrUpdate(type, result)) return "/admin/types-input";

        Type t = typeService.saveType(type);
        if (t==null){
            attributes.addFlashAttribute("message","操作失败");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("types/{id}")
    public String editPost(@Valid Type type,
                           BindingResult result ,
                           @PathVariable("id") Long id,
                           RedirectAttributes attributes){
        if (validInsertOrUpdate(type, result)) return "/admin/types-input";

        Type t = typeService.update(id,type);
        if (t==null){
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("types/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
    private boolean validInsertOrUpdate(@Valid Type type, BindingResult result) {
        Type dbType = typeService.getTypeByName(type.getName());
        if (dbType != null){
            result.rejectValue("name", "nameError", "分类名称不能重复");
        }
        if (result.hasErrors()){
            return true;
        }
        return false;
    }

}
