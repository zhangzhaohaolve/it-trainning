package com.company.training.modules.exam.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("exam_score")
public class ExamScore {
    private Long id;
    private Long examId;
    private Long userId;
    private String answers;
    private Integer score;
    private LocalDateTime submitTime;
    private Integer status;
    private Long reviewer;
    private LocalDateTime reviewTime;
    private Integer deleted;
    private LocalDateTime createTime;
}
