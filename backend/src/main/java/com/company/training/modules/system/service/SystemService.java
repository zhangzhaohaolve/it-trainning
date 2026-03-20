package com.company.training.modules.system.service;

import com.company.training.modules.system.dto.*;
import com.company.training.modules.system.entity.*;

import java.util.List;
import java.util.Map;

public interface SystemService {
    Map<String, Object> login(LoginDTO dto, String ip);
    void logout();

    List<SysUser> listUsers(String name);
    Long saveUser(SysUserDTO dto);
    void updateUser(SysUserDTO dto);
    void deleteUser(Long id);
    void resetPassword(Long id, String password);
    void updateUserStatus(Long id, Integer status);

    List<SysRole> listRoles();
    Long saveRole(SysRoleDTO dto);
    void updateRole(SysRoleDTO dto);
    void deleteRole(Long id);
    void bindRolePermissions(SysRoleDTO dto);

    List<SysDept> listDepts();
    Long saveDept(SysDeptDTO dto);
    void updateDept(SysDeptDTO dto);
    void deleteDept(Long id);

    List<SysPermission> listPermissions();
    Long savePermission(SysPermissionDTO dto);
    void updatePermission(SysPermissionDTO dto);
    void deletePermission(Long id);

    List<SysLog> listSysLogs();
    List<LoginLog> listLoginLogs();

    List<SysConfig> listConfigs();
    void updateConfig(SysConfigDTO dto);
}
