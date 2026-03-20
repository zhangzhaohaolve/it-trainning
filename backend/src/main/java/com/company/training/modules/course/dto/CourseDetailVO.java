package com.company.training.modules.course.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseDetailVO {
    private Long id;
    private String name;
    private Long categoryId;
    private String categoryName;
    private Long lecturerId;
    private String lecturerName;
    private String description;
    private String coverUrl;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
