package com.company.training.modules.statistics.controller;

import com.company.training.common.result.Result;
import com.company.training.modules.statistics.dto.*;
import com.company.training.modules.statistics.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/participate")
    public Result<List<DeptParticipateVO>> participate(StatisticsQueryDTO dto) {
        return Result.success(statisticsService.participateRate(dto));
    }

    @GetMapping("/completion")
    public Result<List<CourseCompletionVO>> completion(StatisticsQueryDTO dto) {
        return Result.success(statisticsService.completionRate(dto));
    }

    @GetMapping("/passrate")
    public Result<PassRateTrendVO> passrate(StatisticsQueryDTO dto) {
        return Result.success(statisticsService.passRateTrend(dto));
    }
}
