package com.company.training.modules.statistics.dto;

import lombok.Data;

import java.util.List;

@Data
public class PassRateTrendVO {
    private List<String> dates;
    private List<Double> examPass;
    private List<Double> makeupPass;
}
