package com.company.training.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_permission")
public class SysPermission {
    private Long id;
    private String name;
    private String code;
    private String type;
    private Long parentId;
    private String path;
    private String component;
    private String icon;
    private Integer deleted;
}
