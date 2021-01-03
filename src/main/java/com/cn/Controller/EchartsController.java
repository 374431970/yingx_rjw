package com.cn.Controller;

import com.cn.entity.City;
import com.cn.entity.Pro;
import com.cn.po.CityPO;
import com.cn.po.SexPO;
import com.cn.service.UserService;
import com.cn.util.MonthUtil;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author EchartsController
 * @time 2020/12/28-10:16
 */
@RestController
@RequestMapping("echarts")

public class EchartsController {
    @Resource
    UserService userService;
    @RequestMapping("getUserData")
    public HashMap<String, Object> getUserData(){
        HashMap<String, Object> map = new HashMap<>();

        List<Integer> list = MonthUtil.queryMonths();
        List<Integer> boys = new ArrayList<>();
        List<Integer> gitls = new ArrayList<>();
        List<String> monthName = new ArrayList<>();


        String mm = new SimpleDateFormat("MM").format(new Date());
        Integer month = Integer.valueOf(mm);
        for (Integer integer : list) {
            boys.add(userService.findAllByMonth("男", integer));
            gitls.add(userService.findAllByMonth("女", integer));
            monthName.add(integer + "月");
        }
        map.put("months", monthName);
        map.put("boys", boys);
        map.put("gitls", gitls);
        return map;

    }
    @ResponseBody
    @RequestMapping("findAllBySes")
    public List<SexPO> findAllBySes() {
        List<SexPO> sexList = new ArrayList<>();
        List<CityPO> cityBoy = userService.findAllBySex("男");
        sexList.add(new SexPO("小男孩", cityBoy));
        List<CityPO> cityGitl = userService.findAllBySex("女");
        sexList.add(new SexPO("小姑娘", cityGitl));
        return sexList;
    }
}