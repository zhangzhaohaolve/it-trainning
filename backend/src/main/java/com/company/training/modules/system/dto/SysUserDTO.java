package com.company.training.modules.system.dto;

import lombok.Data;

@Data
public class SysUserDTO {
    private Long id;
    private String username;
    private String password;
    private String name;
    private Long deptId;
    private Integer status;
    private Long roleId;
}
