package com.company.training.modules.exam.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("exam_question")
public class ExamQuestion {
    private Long id;
    private Long examId;
    private Long questionId;
    private Integer score;
    private Integer sort;
    private Integer deleted;
}
