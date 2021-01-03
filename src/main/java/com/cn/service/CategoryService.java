package com.cn.service;

import com.cn.entity.Category;
import com.cn.po.CategoryPO;

import java.util.HashMap;
import java.util.List;

public interface CategoryService {
    //查询一级
    HashMap<String,Object> queryAll1(Integer page, Integer rows);
    //查询二级
    HashMap<String,Object> queryAll2(Integer page, Integer rows, String id);
    //添加类别
    String addCategory(Category category,String pid);
    //删除类别
    void delete(Category category);
    //修改类别
    void edit(Category category);

    List<CategoryPO> queryAllCategory();
    }
