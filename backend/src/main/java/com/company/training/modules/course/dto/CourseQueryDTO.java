package com.company.training.modules.course.dto;

import com.company.training.common.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CourseQueryDTO extends PageRequest {
    private String name;
    private Long categoryId;
    private Integer status;
}
