package com.cn.dao;

import com.cn.entity.User;
import com.cn.entity.Video;
import com.cn.entity.VideoExample;
import java.util.List;

import com.cn.po.VideoPO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
public interface VideoDao extends Mapper<Video> {
    List<VideoPO> queryByReleaseTime();
}