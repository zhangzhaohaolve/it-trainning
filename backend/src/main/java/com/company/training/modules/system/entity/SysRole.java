package com.company.training.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sys_role")
public class SysRole {
    private Long id;
    private String name;
    private String code;
    private String remark;
    private LocalDateTime createTime;
    private Integer deleted;
}
