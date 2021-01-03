package com.cn.Controller;

import com.cn.entity.Admin;
import com.cn.entity.User;
import com.cn.service.AdminService;
import com.cn.util.CreateValidateCode;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * @PackageName
 * @ClassName AdminController
 * @Description
 * @Author
 * @Date 2020/12/20 13:06
 */
@Controller
@RequestMapping("/admin")
public class AdminController{
    @Autowired
    AdminService AdminService;
    //管理員登錄
    @ResponseBody
    @RequestMapping("/login")
    public HashMap<String, Object> login(Admin admin, String enCode){
        return AdminService.login(admin, enCode);
    }
    //驗證碼
    @RequestMapping("/enCode")
    public String code(HttpSession session, HttpServletResponse response) throws IOException {
        CreateValidateCode vCode = new CreateValidateCode(100, 30, 5, 10);
        session.setAttribute("code", vCode.getCode()); //保存在Session作用域
        vCode.write(response.getOutputStream());
        System.out.println(vCode.getCode());
        return null;
    }
    //管理員退出
    @RequestMapping("excp")
    public String excp(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:/login/login.jsp";
    }

    @ResponseBody
    @RequestMapping("/queryAdmin")
    public HashMap<String, Object> queryAdmin(Integer page, Integer rows){
        return AdminService.queryAdmin(page, rows);
    }
    @ResponseBody
    @RequestMapping("edit")
    public String edit(Admin admin, String oper){
        String id =null;
        if(oper.equals("add")){
            id = AdminService.add(admin);
        }
        if(oper.equals("edit")){
            AdminService.edit(admin);
        }
        if(oper.equals("del")){
            AdminService.del(admin);
        }
        return id;
    }

}


