package com.company.training.modules.learning.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyLearningVO {
    private Long planId;
    private Long courseId;
    private String courseName;
    private Integer progress;
    private Integer status;
    private LocalDateTime deadline;
}
