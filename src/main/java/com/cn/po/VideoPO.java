package com.cn.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackageName
 * @ClassName VideoPO
 * @Description
 * @Author
 * @Date 2020/12/27 15:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoPO {
    private String id;
    private String title;
    private String description;
    private String videoPath;
    private String coverPath;
    private String uploadTime;
    private String categoryId;
    private String groupId;
    private String userId;
    private String headImg;
    private String cateName;
}
