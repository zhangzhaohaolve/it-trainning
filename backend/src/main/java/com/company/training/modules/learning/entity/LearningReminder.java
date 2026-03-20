package com.company.training.modules.learning.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("learning_reminder")
@Schema(description = "学习提醒")
public class LearningReminder {
    private Long id;
    private Long userId;
    private Long courseId;
    private Integer reminderType;
    private String content;
    private LocalDateTime reminderTime;
    private Integer status;
    private Integer deleted;
}
