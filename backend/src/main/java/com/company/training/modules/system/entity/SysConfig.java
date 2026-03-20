package com.company.training.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sys_config")
public class SysConfig {
    private Long id;
    private String configKey;
    private String configValue;
    private String remark;
    private LocalDateTime updateTime;
}
