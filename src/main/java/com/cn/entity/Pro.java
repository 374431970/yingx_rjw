package com.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pro
 * @time 2020/12/28-11:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pro {

    private String sex;
    private ArrayList<City> citys;

}
