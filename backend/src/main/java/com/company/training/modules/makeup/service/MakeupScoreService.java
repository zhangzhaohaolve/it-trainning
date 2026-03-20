package com.company.training.modules.makeup.service;

import com.company.training.common.page.PageResult;
import com.company.training.modules.makeup.dto.*;
import com.company.training.modules.makeup.entity.MakeupScore;

import java.util.Map;

public interface MakeupScoreService {
    Long submit(MakeupScoreSubmitDTO dto);
    void review(MakeupScoreReviewDTO dto);
    PageResult<MakeupScore> list(MakeupScoreQueryDTO dto);
    Map<String, Object> compareWithOriginal(Long makeupId, Long userId);
}
