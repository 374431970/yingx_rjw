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
public class VideoVO {
    private String id;
    private String videoTitle;
    private String cover;
    private String path;
    private String uploadTime;
    private String description;
    private Integer likeCount;
    private String cateName;
    private String userPhoto;
}
