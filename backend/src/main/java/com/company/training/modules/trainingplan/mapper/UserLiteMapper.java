package com.company.training.modules.trainingplan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserLiteMapper {
    List<Long> selectUserIdsByDeptId(@Param("deptId") Long deptId);
}
