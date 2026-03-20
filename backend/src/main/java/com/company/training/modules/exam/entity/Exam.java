package com.company.training.modules.exam.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("exam")
@Schema(description = "考试")
public class Exam {
    private Long id;
    private Long courseId;
    private String title;
    private Integer duration;
    private Integer totalScore;
    private Integer passScore;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer deleted;
}
