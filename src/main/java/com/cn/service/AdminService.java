package com.cn.service;

import com.cn.entity.Admin;
import com.cn.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface AdminService {
    //管理员登录
    HashMap<String, Object> login(Admin admin, String enCode);
    //查管理员
    HashMap<String,Object> queryAdmin(Integer page, Integer rows);
    //添加
    String add(Admin admin);
    //修改
    void edit(Admin admin);
    //删除
    void del(Admin admin);
}
