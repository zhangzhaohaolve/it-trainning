package com.company.training.modules.makeup.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("makeup_schedule")
public class MakeupSchedule {
    private Long id;
    private Long makeupId;
    private LocalDateTime makeupTime;
    private String location;
    private LocalDateTime createTime;
    private Integer deleted;
}
