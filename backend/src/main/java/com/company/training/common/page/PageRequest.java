package com.company.training.common.page;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PageRequest {
    @Min(value = 1, message = "pageNum must be >= 1")
    private Integer pageNum = 1;

    @Min(value = 1, message = "pageSize must be >= 1")
    @Max(value = 200, message = "pageSize must be <= 200")
    private Integer pageSize = 10;

    public int offset() {
        return (pageNum - 1) * pageSize;
    }
}
