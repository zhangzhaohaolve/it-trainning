package com.company.training.modules.course.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("lecturer")
@Schema(description = "讲师")
public class Lecturer {
    private Long id;
    private String name;
    private String title;
    private String avatarUrl;
    private String bio;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer deleted;
}
