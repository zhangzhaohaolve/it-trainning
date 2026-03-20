package com.company.training.modules.system.service.impl;

import com.company.training.common.exception.BizException;
import com.company.training.modules.system.dto.*;
import com.company.training.modules.system.entity.*;
import com.company.training.modules.system.mapper.*;
import com.company.training.modules.system.security.JwtTokenProvider;
import com.company.training.modules.system.service.SystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SystemServiceImpl implements SystemService {

    private final SysUserMapper userMapper;
    private final SysRoleMapper roleMapper;
    private final SysDeptMapper deptMapper;
    private final SysPermissionMapper permissionMapper;
    private final SysLogMapper sysLogMapper;
    private final LoginLogMapper loginLogMapper;
    private final SysConfigMapper configMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Map<String, Object> login(LoginDTO dto, String ip) {
        SysUser user = userMapper.selectByUsername(dto.getUsername());
        LoginLog log = new LoginLog();
        log.setIp(ip);
        if (user == null || !user.getPassword().equals(dto.getPassword())) {
            log.setUserId(user == null ? null : user.getId());
            log.setStatus("失败");
            loginLogMapper.insert(log);
            throw new BizException("用户名或密码错误");
        }
        if (user.getStatus() == 0) throw new BizException("账号已禁用");
        String token = jwtTokenProvider.createToken(user.getId(), user.getUsername(), permissionMapper.selectCodesByUserId(user.getId()));
        log.setUserId(user.getId()); log.setStatus("成功"); loginLogMapper.insert(log);
        Map<String, Object> map = new HashMap<>(); map.put("token", token); map.put("user", user);
        return map;
    }

    @Override public void logout() {}

    @Override public List<SysUser> listUsers(String name) { return userMapper.selectList(name); }
    @Override public Long saveUser(SysUserDTO dto) { SysUser u = new SysUser(); u.setUsername(dto.getUsername()); u.setPassword(dto.getPassword()); u.setName(dto.getName()); u.setDeptId(dto.getDeptId()); u.setStatus(dto.getStatus()==null?1:dto.getStatus()); userMapper.insert(u); return u.getId(); }
    @Override public void updateUser(SysUserDTO dto) { SysUser u = new SysUser(); u.setId(dto.getId()); u.setUsername(dto.getUsername()); u.setName(dto.getName()); u.setDeptId(dto.getDeptId()); u.setStatus(dto.getStatus()); userMapper.updateById(u); }
    @Override public void deleteUser(Long id) { userMapper.logicDelete(id); }
    @Override public void resetPassword(Long id, String password) { userMapper.resetPassword(id, password); }
    @Override public void updateUserStatus(Long id, Integer status) { userMapper.updateStatus(id, status); }

    @Override public List<SysRole> listRoles() { return roleMapper.selectList(); }
    @Override public Long saveRole(SysRoleDTO dto) { SysRole r = new SysRole(); r.setName(dto.getName()); r.setCode(dto.getCode()); r.setRemark(dto.getRemark()); roleMapper.insert(r); return r.getId(); }
    @Override public void updateRole(SysRoleDTO dto) { SysRole r = new SysRole(); r.setId(dto.getId()); r.setName(dto.getName()); r.setCode(dto.getCode()); r.setRemark(dto.getRemark()); roleMapper.updateById(r); }
    @Override public void deleteRole(Long id) { roleMapper.logicDelete(id); }
    @Override public void bindRolePermissions(SysRoleDTO dto) { roleMapper.clearPermissions(dto.getId()); if (dto.getPermissionIds()!=null && !dto.getPermissionIds().isEmpty()) roleMapper.bindPermissions(dto.getId(), dto.getPermissionIds()); }

    @Override public List<SysDept> listDepts() { return deptMapper.selectList(); }
    @Override public Long saveDept(SysDeptDTO dto) { SysDept d = new SysDept(); d.setName(dto.getName()); d.setParentId(dto.getParentId()); d.setSort(dto.getSort()); deptMapper.insert(d); return d.getId(); }
    @Override public void updateDept(SysDeptDTO dto) { SysDept d = new SysDept(); d.setId(dto.getId()); d.setName(dto.getName()); d.setParentId(dto.getParentId()); d.setSort(dto.getSort()); deptMapper.updateById(d); }
    @Override public void deleteDept(Long id) { deptMapper.logicDelete(id); }

    @Override public List<SysPermission> listPermissions() { return permissionMapper.selectList(); }
    @Override public Long savePermission(SysPermissionDTO dto) { SysPermission p = new SysPermission(); p.setName(dto.getName()); p.setCode(dto.getCode()); p.setType(dto.getType()); p.setParentId(dto.getParentId()); p.setPath(dto.getPath()); p.setComponent(dto.getComponent()); p.setIcon(dto.getIcon()); permissionMapper.insert(p); return p.getId(); }
    @Override public void updatePermission(SysPermissionDTO dto) { SysPermission p = new SysPermission(); p.setId(dto.getId()); p.setName(dto.getName()); p.setCode(dto.getCode()); p.setType(dto.getType()); p.setParentId(dto.getParentId()); p.setPath(dto.getPath()); p.setComponent(dto.getComponent()); p.setIcon(dto.getIcon()); permissionMapper.updateById(p); }
    @Override public void deletePermission(Long id) { permissionMapper.logicDelete(id); }

    @Override public List<SysLog> listSysLogs() { return sysLogMapper.selectList(); }
    @Override public List<LoginLog> listLoginLogs() { return loginLogMapper.selectList(); }

    @Override public List<SysConfig> listConfigs() { return configMapper.selectList(); }
    @Override public void updateConfig(SysConfigDTO dto) { SysConfig c = new SysConfig(); c.setId(dto.getId()); c.setConfigKey(dto.getConfigKey()); c.setConfigValue(dto.getConfigValue()); c.setRemark(dto.getRemark()); configMapper.updateById(c); }
}
