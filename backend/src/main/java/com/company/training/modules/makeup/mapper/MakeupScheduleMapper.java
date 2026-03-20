package com.company.training.modules.makeup.mapper;

import com.company.training.modules.makeup.entity.MakeupSchedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MakeupScheduleMapper {
    int insert(MakeupSchedule s);
    int updateById(MakeupSchedule s);
    List<MakeupSchedule> selectAll();
    MakeupSchedule selectByMakeupId(@Param("makeupId") Long makeupId);
}
