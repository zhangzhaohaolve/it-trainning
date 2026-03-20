package com.company.training.modules.makeup.dto;

import com.company.training.common.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MakeupExamQueryDTO extends PageRequest {
    private Long userId;
    private Long courseId;
    private Long deptId;
}
