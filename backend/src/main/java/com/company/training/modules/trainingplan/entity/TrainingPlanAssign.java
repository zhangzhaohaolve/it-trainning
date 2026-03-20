package com.company.training.modules.trainingplan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("training_plan_assign")
@Schema(description = "培训任务分配")
public class TrainingPlanAssign {
    private Long id;
    private Long planId;
    private Long userId;
    private Integer status;
    private LocalDateTime createTime;
    private Integer deleted;
}
