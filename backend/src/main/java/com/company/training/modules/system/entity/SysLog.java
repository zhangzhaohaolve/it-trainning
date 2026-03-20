package com.company.training.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sys_log")
public class SysLog {
    private Long id;
    private Long userId;
    private String action;
    private String method;
    private String params;
    private String ip;
    private LocalDateTime createTime;
}
