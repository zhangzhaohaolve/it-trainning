package com.company.training.modules.statistics.mapper;

import com.company.training.modules.statistics.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticsMapper {
    List<DeptParticipateVO> selectParticipateRate(StatisticsQueryDTO dto);

    List<CourseCompletionVO> selectCompletionRate(StatisticsQueryDTO dto);

    List<PassRateRowVO> selectPassRateTrend(StatisticsQueryDTO dto);
}
