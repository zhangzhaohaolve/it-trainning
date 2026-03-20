package com.company.training.modules.course.service;

import com.company.training.modules.course.entity.CourseMaterial;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseMaterialService {
    String upload(Long courseId, MultipartFile file);

    void delete(Long id);

    List<CourseMaterial> listByCourseId(Long courseId);
}
