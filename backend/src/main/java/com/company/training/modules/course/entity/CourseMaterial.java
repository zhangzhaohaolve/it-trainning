package com.company.training.modules.course.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("course_material")
@Schema(description = "课件")
public class CourseMaterial {
    private Long id;
    private Long courseId;
    private String fileName;
    private String fileUrl;
    private String fileType;
    private Long size;
    private LocalDateTime createTime;
    private Integer deleted;
}
