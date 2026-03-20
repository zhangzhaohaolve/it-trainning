package com.company.training.modules.course.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("course")
@Schema(description = "课程")
public class Course {
    private Long id;
    private String name;
    private Long categoryId;
    private Long lecturerId;
    private String description;
    private String coverUrl;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer deleted;
}
