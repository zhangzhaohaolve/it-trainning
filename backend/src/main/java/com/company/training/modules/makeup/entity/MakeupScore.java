package com.company.training.modules.makeup.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("makeup_score")
public class MakeupScore {
    private Long id;
    private Long makeupId;
    private Long userId;
    private String answers;
    private Integer score;
    private LocalDateTime submitTime;
    private Integer status;
    private Long reviewer;
    private LocalDateTime reviewTime;
    private Integer deleted;
}
