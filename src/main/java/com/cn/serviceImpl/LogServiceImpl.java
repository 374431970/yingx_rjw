package com.cn.serviceImpl;

import com.cn.dao.LogDao;
import com.cn.dao.UserDao;
import com.cn.entity.Log;
import com.cn.entity.User;
import com.cn.entity.UserExample;
import com.cn.service.LogService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @PackageName
 * @ClassName LogServiceImpl
 * @Description
 * @Author
 * @Date 2020/12/25 9:27
 */
@Service("LogService")
@Transactional
public class LogServiceImpl implements LogService {
    @Resource
    LogDao dao;

    @Override
    public HashMap<String, Object> querylog(Integer page, Integer rows) {
        // Integer page, Integer rows(每页展示条数)
        //返回  page=当前页   rows=[User,User]数据    tolal=总页数   records=总条数
        HashMap<String, Object> map = new HashMap<>();

        //设置当前页
        map.put("page",page);
        //创建条件对象
        UserExample example = new UserExample();
        //创建分页对象   参数：从第几条开始，展示几条
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        //查询数据
        List<Log> users = dao.selectByExampleAndRowBounds(example, rowBounds);

        map.put("rows",users);

        //查询总条数
        int records = dao.selectCountByExample(example);
        map.put("records",records);

        //计算总页数
        Integer tolal=records%rows==0?records/rows:records/rows+1;
        map.put("total",tolal);

        return map;
    }
}
