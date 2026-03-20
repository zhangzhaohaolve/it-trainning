package com.company.training.modules.trainingplan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("training_notice")
@Schema(description = "培训通知")
public class TrainingNotice {
    private Long id;
    private Long planId;
    private Long userId;
    private Integer type;
    private String content;
    private LocalDateTime sendTime;
    private Integer status;
    private Integer deleted;
}
