package com.cn.Controller;

import com.cn.entity.Category;
import com.cn.entity.User;
import com.cn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.Column;
import java.util.HashMap;

/**
 * @PackageName
 * @ClassName CategoryController
 * @Description
 * @Author
 * @Date 2020/12/22 16:41
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @ResponseBody
    @RequestMapping("/oneCategory")
    public HashMap<String, Object> queryAll1(Integer page, Integer rows){
        return categoryService.queryAll1(page, rows);
    }
    @ResponseBody
    @RequestMapping("/TwoCategory")
    public HashMap<String, Object> queryAll2(Integer page, Integer rows,String categoryId){
        return categoryService.queryAll2(page, rows, categoryId);
    }
    @ResponseBody
    @RequestMapping("edit")
    public String edit(Category category, String oper,String pid){
        if(oper.equals("add")){
            categoryService.addCategory(category,pid);
        }
        if(oper.equals("del")){
            categoryService.delete(category);
        }
        if(oper.equals("edit")){
            categoryService.edit(category);
        }
        return null;
    }
}
