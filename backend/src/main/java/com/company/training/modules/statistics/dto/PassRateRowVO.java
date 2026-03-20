package com.company.training.modules.statistics.dto;

import lombok.Data;

@Data
public class PassRateRowVO {
    private String statDate;
    private Double examPassRate;
    private Double makeupPassRate;
}
