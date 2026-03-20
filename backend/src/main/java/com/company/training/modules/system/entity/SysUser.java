package com.company.training.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sys_user")
public class SysUser {
    private Long id;
    private String username;
    private String password;
    private String name;
    private Long deptId;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer deleted;
}
