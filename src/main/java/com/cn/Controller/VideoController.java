package com.cn.Controller;

import com.cn.entity.Admin;
import com.cn.entity.Video;
import com.cn.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @PackageName
 * @ClassName VideoController
 * @Description
 * @Author
 * @Date 2020/12/23 19:50
 */
@Controller
@RequestMapping("/video")
public class VideoController {
    @Resource
    VideoService videoService;

    @ResponseBody
    @RequestMapping("/queryVideo")
    public HashMap<String, Object> queryAdmin(Integer page, Integer rows){
        return videoService.queryVideo(page, rows);
    }
    @ResponseBody
    @RequestMapping("edit")
    public String edit(Video video, String oper){
        String id =null;
        if(oper.equals("add")){
            id = videoService.add(video);
        }
        if(oper.equals("edit")){
            videoService.edit(video);
        }
        if(oper.equals("del")){
            videoService.del(video);
        }
        return id;
    }
    @ResponseBody
    @RequestMapping("uploadVdieo")
    public void uploadVdieo(MultipartFile videoPath, String id, HttpServletRequest request) {
        videoService.uploadVdieosAliyun(videoPath, id, request);
    }
}
