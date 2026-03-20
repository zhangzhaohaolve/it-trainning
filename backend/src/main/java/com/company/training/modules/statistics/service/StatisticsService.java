package com.company.training.modules.statistics.service;

import com.company.training.modules.statistics.dto.*;

import java.util.List;

public interface StatisticsService {
    List<DeptParticipateVO> participateRate(StatisticsQueryDTO dto);

    List<CourseCompletionVO> completionRate(StatisticsQueryDTO dto);

    PassRateTrendVO passRateTrend(StatisticsQueryDTO dto);
}
