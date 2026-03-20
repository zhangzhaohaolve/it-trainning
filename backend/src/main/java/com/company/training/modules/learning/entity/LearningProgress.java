package com.company.training.modules.learning.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("learning_progress")
@Schema(description = "学习进度")
public class LearningProgress {
    private Long id;
    private Long userId;
    private Long courseId;
    private Integer progress;
    private LocalDateTime lastLearnTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer deleted;
}
