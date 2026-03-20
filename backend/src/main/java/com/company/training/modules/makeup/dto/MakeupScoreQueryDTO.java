package com.company.training.modules.makeup.dto;

import com.company.training.common.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MakeupScoreQueryDTO extends PageRequest {
    private Long makeupId;
    private Long userId;
}
