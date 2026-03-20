package com.company.training.modules.system.mapper;

import com.company.training.modules.system.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper {
    int insert(SysUser u);
    int updateById(SysUser u);
    int logicDelete(@Param("id") Long id);
    SysUser selectById(@Param("id") Long id);
    SysUser selectByUsername(@Param("username") String username);
    List<SysUser> selectList(@Param("name") String name);
    int resetPassword(@Param("id") Long id,@Param("password") String password);
    int updateStatus(@Param("id") Long id,@Param("status") Integer status);
}
