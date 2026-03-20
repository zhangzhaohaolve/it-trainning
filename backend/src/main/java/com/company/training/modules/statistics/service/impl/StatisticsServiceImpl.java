package com.company.training.modules.statistics.service.impl;

import com.company.training.modules.statistics.dto.*;
import com.company.training.modules.statistics.mapper.StatisticsMapper;
import com.company.training.modules.statistics.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsMapper statisticsMapper;

    @Override
    public List<DeptParticipateVO> participateRate(StatisticsQueryDTO dto) {
        return statisticsMapper.selectParticipateRate(dto);
    }

    @Override
    public List<CourseCompletionVO> completionRate(StatisticsQueryDTO dto) {
        return statisticsMapper.selectCompletionRate(dto);
    }

    @Override
    public PassRateTrendVO passRateTrend(StatisticsQueryDTO dto) {
        List<PassRateRowVO> rows = statisticsMapper.selectPassRateTrend(dto);
        PassRateTrendVO vo = new PassRateTrendVO();
        List<String> dates = new ArrayList<>();
        List<Double> exam = new ArrayList<>();
        List<Double> makeup = new ArrayList<>();
        for (PassRateRowVO row : rows) {
            dates.add(row.getStatDate());
            exam.add(row.getExamPassRate());
            makeup.add(row.getMakeupPassRate());
        }
        vo.setDates(dates);
        vo.setExamPass(exam);
        vo.setMakeupPass(makeup);
        return vo;
    }
}
