package com.company.training.modules.system.mapper;

import com.company.training.modules.system.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysLogMapper {
    int insert(SysLog l);
    List<SysLog> selectList();
}
