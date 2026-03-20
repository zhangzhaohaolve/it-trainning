package com.company.training.modules.system.dto;

import lombok.Data;

import java.util.List;

@Data
public class SysRoleDTO {
    private Long id;
    private String name;
    private String code;
    private String remark;
    private List<Long> permissionIds;
}
