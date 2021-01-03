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

@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "yx_log")
public class Log {
    @Id
    private String id;
    @Column(name="log_name")
    private String logName;
    @DateTimeFormat(pattern = "yyyy-MM-dd ")  //设置接受日期格式
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name ="log_times")
    private Date logTimes;
    @Column(name ="log_option")
    private String logOption;
    private String status;
    }