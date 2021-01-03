package com.cn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="yx_user")
public class User {
    @Id
    private String id;

    private String phone;//手机号
    @Column(name="head_Img")
    private String headImg;//用户头像

    private String username;//用户名字

    private String brief;//简介

    private Integer status;//状态

    @DateTimeFormat(pattern = "yyyy-MM-dd ")  //设置接受日期格式
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "create_date")
    private Date createDate;//注册时间

    private String score;//学分

    private String sex;//性别
}