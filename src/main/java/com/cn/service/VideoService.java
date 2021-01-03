package com.cn.service;

import com.cn.entity.Admin;
import com.cn.entity.Video;
import com.cn.po.VideoPO;
import com.cn.po.VideoVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface VideoService {
    //查所有视频
    HashMap<String,Object> queryVideo(Integer page, Integer rows);
    //添加
    String add(Video video);
    //修改
    void edit(Video video);
    //删除
    void del(Video video);
    //视频上传 截图上传
    void uploadVdieos(MultipartFile videoPath, String id, HttpServletRequest request);

    void uploadVdieosAliyun(MultipartFile videoPath, String id, HttpServletRequest request);

    List<VideoVO> queryByReleaseTime();
}
