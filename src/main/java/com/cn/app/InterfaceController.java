package com.cn.app;

import com.cn.commo.CommoResult;
import com.cn.po.CategoryPO;
import com.cn.po.VideoVO;
import com.cn.service.CategoryService;
import com.cn.service.VideoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @PackageName
 * @ClassName InterfaceController
 * @Description
 * @Author
 * @Date 2020/12/27 15:18
 */



@RestController
@RequestMapping("app")
public class InterfaceController {
    @Resource
    VideoService videoService;
    @Resource
    CategoryService categoryService;

    @RequestMapping("queryByReleaseTime")
    public CommoResult queryByReleaseTime(){
        try {
            List<VideoVO> videoVOS = videoService.queryByReleaseTime();
            return new CommoResult().success(videoVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommoResult().filed();
        }
    }
    @RequestMapping("queryAllCategory")
    public CommoResult queryAllCategory(){
        try {
            List<CategoryPO> CategoryVOS = categoryService.queryAllCategory();
            return new CommoResult().success(CategoryVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommoResult().filed();
        }
    }
}
