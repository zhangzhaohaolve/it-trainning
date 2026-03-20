package com.company.training.modules.makeup.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("makeup_exam")
public class MakeupExam {
    private Long id;
    private Long examId;
    private Long userId;
    private String reason;
    private LocalDateTime createTime;
    private Integer status;
    private Integer deleted;
}
