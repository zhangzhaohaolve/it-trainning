package com.company.training.modules.statistics.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StatisticsQueryDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long deptId;
    private Long categoryId;
}
