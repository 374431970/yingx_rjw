package com.cn.serviceImpl;

import com.cn.annotation.AddLog;
import com.cn.dao.AdminDao;
import com.cn.entity.Admin;
import com.cn.entity.AdminExample;
import com.cn.entity.User;
import com.cn.entity.UserExample;
import com.cn.service.AdminService;
import com.cn.util.Md5Utils;
import com.cn.util.UUIDUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @PackageName
 * @ClassName AdminServiceImpl
 * @Description
 * @Author
 * @Date 2020/12/20 12:46
 */
@Service("Adminservice")
@Transactional
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminDao adminDao;
    @Resource
    HttpSession session;
    //管理员登录
    @Override
    public HashMap<String, Object> login(Admin admin, String enCode) {
        //获取验证码
        String imageCode = (String) session.getAttribute("code");

        HashMap<String, Object> map = new HashMap<>();

        Admin admins = adminDao.loginAdmin(admin.getName());

        String password = Md5Utils.getMd5Code(admin.getPassword()+admins.getSalt());

        //判断验证码
        if(enCode.equals(imageCode)){
            //判断用户是否存在
            if(admins!=null){
                //判断用户状态
                if(admins.getStatus().equals("1")){
                    if(admins.getPassword().equals(password)){

                        //存储用户标记
                        session.setAttribute("admin",admins);

                        map.put("status","200");
                        map.put("message","登录成功");
                    }else{
                        map.put("status","401");
                        map.put("message","密码不正确");
                    }
                }else{
                    map.put("status","401");
                    map.put("message","该用户已冻结");
                }
            }else{
                map.put("status","401");
                map.put("message","该用户不存在");
            }
        }else{
            map.put("status","401");
            map.put("message","验证码不正确");
        }

        return map;
    }

    @Override
    public HashMap<String, Object> queryAdmin(Integer page, Integer rows) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("page",page);
        AdminExample example=new AdminExample();
        //创建分页对象
        RowBounds rowBounds=new RowBounds((page-1)*rows,rows);
        List<Admin> list=adminDao.selectByExampleAndRowBounds(example,rowBounds);

        map.put("rows",list);
        //查询总条数
        int records = adminDao.selectCountByExample(example);
        map.put("records",records);

        //计算总页数
        Integer tolal=records%rows==0?records/rows:records/rows+1;
        map.put("total",tolal);
        return map;
    }
    @AddLog(value = "添加管理员")
    @Override
    public String add(Admin admin) {
        String uuid = UUIDUtil.getUUID();
        String salt = Md5Utils.getSalt(5);
        String md5util = Md5Utils.getMd5Code(admin.getPassword()+salt);
        admin.setId(uuid);
        admin.setStatus("1");
        admin.setPassword(md5util);
        admin.setSalt(salt);
        adminDao.insertSelective(admin);

        //添加方法返回id
        return uuid;
    }
    @AddLog(value = "修改管理员状态")
    @Override
    public void edit(Admin admin) {
        adminDao.updateByPrimaryKeySelective(admin);
    }
    @AddLog(value = "删除管理员")
    @Override
    public void del(Admin admin) {
        adminDao.deleteByPrimaryKey(admin);
    }


}
