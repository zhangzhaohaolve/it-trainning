package com.company.training.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("login_log")
public class LoginLog {
    private Long id;
    private Long userId;
    private String ip;
    private LocalDateTime loginTime;
    private String status;
}
