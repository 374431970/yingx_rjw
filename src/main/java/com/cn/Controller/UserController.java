package com.cn.Controller;

import com.cn.entity.Admin;
import com.cn.entity.User;
import com.cn.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackageName
 * @ClassName UserController
 * @Description
 * @Author
 * @Date 2020/12/21 16:55
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService Userservice;

    @ResponseBody
    @RequestMapping("/queryUserPage")
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows){
        return Userservice.queryUserPage(page, rows);
    }

    @ResponseBody
    @RequestMapping("edit")
    public String edit(User user, String oper){
        String id =null;
        if(oper.equals("add")){
            id = Userservice.add(user);
        }
        if(oper.equals("edit")){
            Userservice.edit(user);
        }
        if(oper.equals("del")){
            Userservice.del(user);
        }
        return id;
    }
    @ResponseBody
    @RequestMapping("uploadUserCover")
    public void uploadUserCover(MultipartFile headImg, String id, HttpServletRequest request){
        Userservice.uploadUserCover(headImg,id,request);
    }

}
