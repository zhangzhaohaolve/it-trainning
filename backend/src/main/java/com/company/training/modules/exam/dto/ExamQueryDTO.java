package com.company.training.modules.exam.dto;

import com.company.training.common.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ExamQueryDTO extends PageRequest {
    private Long courseId;
    private Integer status;
}
