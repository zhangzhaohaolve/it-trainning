package com.company.training.modules.system.controller;

import com.company.training.common.result.Result;
import com.company.training.modules.system.dto.*;
import com.company.training.modules.system.entity.*;
import com.company.training.modules.system.service.SystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/system")
@RequiredArgsConstructor
public class SystemController {

    private final SystemService systemService;

    @PostMapping("/login") public Result<Map<String, Object>> login(@RequestBody LoginDTO dto, HttpServletRequest request){ return Result.success(systemService.login(dto, request.getRemoteAddr())); }
    @PostMapping("/logout") public Result<Void> logout(){ systemService.logout(); return Result.success(); }

    @GetMapping("/user") public Result<List<SysUser>> userList(@RequestParam(required = false) String name){ return Result.success(systemService.listUsers(name)); }
    @PostMapping("/user") public Result<Long> addUser(@RequestBody SysUserDTO dto){ return Result.success(systemService.saveUser(dto)); }
    @PutMapping("/user") public Result<Void> editUser(@RequestBody SysUserDTO dto){ systemService.updateUser(dto); return Result.success(); }
    @DeleteMapping("/user/{id}") public Result<Void> delUser(@PathVariable Long id){ systemService.deleteUser(id); return Result.success(); }
    @PutMapping("/user/reset-password/{id}") public Result<Void> resetPwd(@PathVariable Long id,@RequestParam String password){ systemService.resetPassword(id,password); return Result.success(); }
    @PutMapping("/user/status/{id}") public Result<Void> userStatus(@PathVariable Long id,@RequestParam Integer status){ systemService.updateUserStatus(id,status); return Result.success(); }

    @GetMapping("/role") public Result<List<SysRole>> roleList(){ return Result.success(systemService.listRoles()); }
    @PostMapping("/role") public Result<Long> addRole(@RequestBody SysRoleDTO dto){ return Result.success(systemService.saveRole(dto)); }
    @PutMapping("/role") public Result<Void> editRole(@RequestBody SysRoleDTO dto){ systemService.updateRole(dto); return Result.success(); }
    @DeleteMapping("/role/{id}") public Result<Void> delRole(@PathVariable Long id){ systemService.deleteRole(id); return Result.success(); }
    @PutMapping("/role/permission") public Result<Void> bindRolePerm(@RequestBody SysRoleDTO dto){ systemService.bindRolePermissions(dto); return Result.success(); }

    @GetMapping("/dept") public Result<List<SysDept>> deptList(){ return Result.success(systemService.listDepts()); }
    @PostMapping("/dept") public Result<Long> addDept(@RequestBody SysDeptDTO dto){ return Result.success(systemService.saveDept(dto)); }
    @PutMapping("/dept") public Result<Void> editDept(@RequestBody SysDeptDTO dto){ systemService.updateDept(dto); return Result.success(); }
    @DeleteMapping("/dept/{id}") public Result<Void> delDept(@PathVariable Long id){ systemService.deleteDept(id); return Result.success(); }

    @GetMapping("/permission") public Result<List<SysPermission>> permList(){ return Result.success(systemService.listPermissions()); }
    @PostMapping("/permission") public Result<Long> addPerm(@RequestBody SysPermissionDTO dto){ return Result.success(systemService.savePermission(dto)); }
    @PutMapping("/permission") public Result<Void> editPerm(@RequestBody SysPermissionDTO dto){ systemService.updatePermission(dto); return Result.success(); }
    @DeleteMapping("/permission/{id}") public Result<Void> delPerm(@PathVariable Long id){ systemService.deletePermission(id); return Result.success(); }

    @GetMapping("/log") public Result<List<SysLog>> sysLog(){ return Result.success(systemService.listSysLogs()); }
    @GetMapping("/loginlog") public Result<List<LoginLog>> loginLog(){ return Result.success(systemService.listLoginLogs()); }

    @GetMapping("/config") public Result<List<SysConfig>> configList(){ return Result.success(systemService.listConfigs()); }
    @PutMapping("/config") public Result<Void> updateConfig(@RequestBody SysConfigDTO dto){ systemService.updateConfig(dto); return Result.success(); }
}
