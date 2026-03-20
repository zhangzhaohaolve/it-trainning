package com.company.training.modules.exam.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("question")
@Schema(description = "题库")
public class Question {
    private Long id;
    private Long courseId;
    private Integer type;
    private String content;
    private String options;
    private String answer;
    private Integer difficulty;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer deleted;
}
