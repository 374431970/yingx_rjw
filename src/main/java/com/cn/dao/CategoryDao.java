package com.cn.dao;

import com.cn.entity.Category;
import com.cn.po.CategoryPO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface CategoryDao extends  Mapper<Category> {
    //前台查询类别
    List<CategoryPO> queryAllCategory();
}