package com.company.training.modules.system.mapper;

import com.company.training.modules.system.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    int insert(SysRole r);
    int updateById(SysRole r);
    int logicDelete(@Param("id") Long id);
    List<SysRole> selectList();
    int bindPermissions(@Param("roleId") Long roleId,@Param("permissionIds") List<Long> permissionIds);
    int clearPermissions(@Param("roleId") Long roleId);
}
