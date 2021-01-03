package com.cn;

import com.cn.dao.AdminDao;
import com.cn.dao.CategoryDao;
import com.cn.dao.UserDao;
import com.cn.entity.Admin;
import com.cn.entity.Pro;
import com.cn.po.CategoryPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@SpringBootTest
class YingxRjwApplicationTests {
    @Resource
    UserDao dao;
    @Test
    void contextLoads() {

    }

}
