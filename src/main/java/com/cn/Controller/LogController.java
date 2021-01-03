package com.cn.Controller;

import com.cn.service.LogService;
import com.cn.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @PackageName
 * @ClassName LogController
 * @Description
 * @Author
 * @Date 2020/12/25 9:24
 */
@Controller
@RequestMapping("/log")
public class LogController {
    @Resource
    LogService logService;

    @ResponseBody
    @RequestMapping("/querylog")
    public HashMap<String, Object> querylog(Integer page, Integer rows){
        return logService.querylog(page, rows);
    }
}
