package com.company.training.modules.system.mapper;

import com.company.training.modules.system.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysPermissionMapper {
    int insert(SysPermission p);
    int updateById(SysPermission p);
    int logicDelete(@Param("id") Long id);
    List<SysPermission> selectList();
    List<String> selectCodesByUserId(@Param("userId") Long userId);
}
