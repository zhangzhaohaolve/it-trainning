package com.company.training.modules.system.mapper;

import com.company.training.modules.system.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysConfigMapper {
    int insert(SysConfig c);
    int updateById(SysConfig c);
    List<SysConfig> selectList();
    SysConfig selectByKey(@Param("configKey") String key);
}
