package com.company.training.modules.learning.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("learning_record")
@Schema(description = "学习记录档案")
public class LearningRecord {
    private Long id;
    private Long userId;
    private Long courseId;
    private Integer duration;
    private LocalDateTime finishTime;
    private LocalDateTime createTime;
    private Integer deleted;
}
