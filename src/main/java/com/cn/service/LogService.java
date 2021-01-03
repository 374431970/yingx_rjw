package com.cn.service;

import java.util.HashMap;

public interface LogService {
    //查数据
    HashMap<String,Object> querylog(Integer page, Integer rows);
}
