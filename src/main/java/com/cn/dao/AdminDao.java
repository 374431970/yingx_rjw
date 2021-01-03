package com.cn.dao;

import com.cn.entity.Admin;
import com.cn.entity.AdminExample;
import java.util.List;

import com.cn.entity.User;
import tk.mybatis.mapper.common.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;

@org.apache.ibatis.annotations.Mapper
public interface AdminDao extends Mapper<Admin> {
    //管理员登录
    public Admin loginAdmin(String name);

}