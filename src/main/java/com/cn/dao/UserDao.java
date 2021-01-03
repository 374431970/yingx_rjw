package com.cn.dao;

import com.cn.entity.User;

import java.util.List;

import com.cn.po.CityPO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface UserDao extends Mapper<User>{
    List<CityPO> findAllBySex(String sex);

    Integer findAllByMonth(@Param("sex") String sex,
                           @Param("month") Integer month);
}