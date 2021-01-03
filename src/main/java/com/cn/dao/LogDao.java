package com.cn.dao;

import com.cn.entity.Log;
import com.cn.entity.LogExample;
import java.util.List;

import com.cn.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
public interface LogDao extends Mapper<Log> {

}