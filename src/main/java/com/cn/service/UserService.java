package com.cn.service;

import com.cn.entity.Pro;
import com.cn.entity.User;
import com.cn.po.CityPO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface UserService {

    HashMap<String,Object> queryUserPage(Integer page, Integer rows);

    String add(User user);

    void uploadUserCover(MultipartFile headImg, String id, HttpServletRequest request);

    void edit(User user);

    void del(User user);

    List<CityPO> findAllBySex(String sex);

    Integer findAllByMonth(String sex,Integer month);

}
