package com.company.training.modules.system.dto;

import lombok.Data;

@Data
public class SysDeptDTO {
    private Long id;
    private String name;
    private Long parentId;
    private Integer sort;
}
