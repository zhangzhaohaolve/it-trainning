package com.company.training.modules.system.mapper;

import com.company.training.modules.system.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginLogMapper {
    int insert(LoginLog l);
    List<LoginLog> selectList();
}
