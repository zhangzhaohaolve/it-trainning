package com.company.training.modules.system.mapper;

import com.company.training.modules.system.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysDeptMapper {
    int insert(SysDept d);
    int updateById(SysDept d);
    int logicDelete(@Param("id") Long id);
    List<SysDept> selectList();
}
