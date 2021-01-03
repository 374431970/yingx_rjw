package com.cn.serviceImpl;

import com.cn.annotation.AddLog;
import com.cn.dao.CategoryDao;
import com.cn.entity.Category;
import com.cn.entity.CategoryExample;
import com.cn.entity.User;
import com.cn.entity.UserExample;
import com.cn.po.CategoryPO;
import com.cn.po.VideoPO;
import com.cn.po.VideoVO;
import com.cn.service.CategoryService;
import com.cn.util.UUIDUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @PackageName
 * @ClassName CategoryServiceImpl
 * @Description
 * @Author
 * @Date 2020/12/22 13:34
 */
@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao dao;


    @Override
    public HashMap<String, Object> queryAll1(Integer page, Integer rows) {
        // Integer page, Integer rows(每页展示条数)
        //返回  page=当前页   rows=[User,User]数据    tolal=总页数   records=总条数
        HashMap<String, Object> map = new HashMap<>();

        //设置当前页
        map.put("page", page);
        //创建条件对象
        CategoryExample example = new CategoryExample();
        //创建分页对象   参数：从第几条开始，展示几条
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        //查询数据
        example.createCriteria().andLevelsEqualTo(1);

        List<Category> categories1 = dao.selectByExampleAndRowBounds(example, rowBounds);

        map.put("rows", categories1);
        //查询总条数
        int records = dao.selectCountByExample(example);
        map.put("records", records);

        //计算总页数
        Integer tolal = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total", tolal);

        return map;
    }

    @Override
    public HashMap<String, Object> queryAll2(Integer page, Integer rows, String id) {
        // Integer page, Integer rows(每页展示条数)
        //返回  page=当前页   rows=[User,User]数据    tolal=总页数   records=总条数
        HashMap<String, Object> map = new HashMap<>();

        //设置当前页
        map.put("page", page);
        //创建条件对象
        CategoryExample example = new CategoryExample();
        //创建分页对象   参数：从第几条开始，展示几条
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        //查询数据
        example.createCriteria().andLevelsEqualTo(2).andParentIdEqualTo(id);

        List<Category> categories2 = dao.selectByExampleAndRowBounds(example, rowBounds);

        map.put("rows", categories2);

        //查询总条数
        int records = dao.selectCountByExample(example);
        map.put("records", records);

        //计算总页数
        Integer tolal = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total", tolal);

        return map;
    }
    @AddLog(value = "添加类别")
    @Override
    public String addCategory(Category category, String pid) {
        String uuid = UUIDUtil.getUUID();
        if (pid == null) {
            category.setId(uuid);
            category.setLevels(1);
            category.setParentId(null);
            dao.insertSelective(category);
        } else {
            category.setId(uuid);
            category.setLevels(2);
            category.setParentId(pid);
            dao.insertSelective(category);
        }
        return uuid;
    }
    @AddLog(value = "删除类别")
    @Override
    public void delete(Category category) {
        Category category1 = dao.selectOne(category);
        if (category1.getLevels() == 1) {
            CategoryExample example = new CategoryExample();
            example.createCriteria().andLevelsEqualTo(2).andParentIdEqualTo(category.getId());
            int count = dao.selectCountByExample(example);
            if (count == 0) {
                dao.deleteByPrimaryKey(category);
            }
        } else dao.deleteByPrimaryKey(category);
        dao.delete(category);
    }
    @AddLog(value = "修改类别")
    @Override
    public void edit(Category category) {
        dao.updateByPrimaryKeySelective(category);
    }

    @Override
    public List<CategoryPO> queryAllCategory() {
        List<CategoryPO> list = dao.queryAllCategory();
        return list;
    }

}
