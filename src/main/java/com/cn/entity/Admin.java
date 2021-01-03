package com.cn.entity;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="yx_admin")
public class Admin {
    @Id
    private String id;

    private String name;

    private String password;

    private String status;

    private String salt;
}