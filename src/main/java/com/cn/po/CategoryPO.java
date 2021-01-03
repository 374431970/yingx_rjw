package com.cn.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @PackageName
 * @ClassName CategoryPO
 * @Description
 * @Author
 * @Date 2020/12/27 17:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPO {
    private String id;
    private String cateName;
    private String levels;
    private String parentId;
    private List<CategoryPO> categoryList;
}
