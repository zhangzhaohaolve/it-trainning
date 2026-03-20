package com.company.training.modules.system.dto;

import lombok.Data;

@Data
public class SysPermissionDTO {
    private Long id;
    private String name;
    private String code;
    private String type;
    private Long parentId;
    private String path;
    private String component;
    private String icon;
}
