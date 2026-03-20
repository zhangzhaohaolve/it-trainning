package com.company.training.modules.course.service.impl;

import com.company.training.common.exception.BizException;
import com.company.training.modules.course.entity.CourseMaterial;
import com.company.training.modules.course.mapper.CourseMapper;
import com.company.training.modules.course.mapper.CourseMaterialMapper;
import com.company.training.modules.course.service.CourseMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseMaterialServiceImpl implements CourseMaterialService {

    private static final long MAX_FILE_SIZE = 500L * 1024 * 1024;

    private final CourseMaterialMapper materialMapper;
    private final CourseMapper courseMapper;

    @Value("${app.upload-dir:uploads}")
    private String uploadDir;

    @Override
    public String upload(Long courseId, MultipartFile file) {
        if (courseMapper.selectById(courseId) == null) {
            throw new BizException("课程不存在");
        }
        if (file == null || file.isEmpty()) {
            throw new BizException("上传文件不能为空");
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new BizException("文件大小不能超过500MB");
        }
        String originalName = file.getOriginalFilename();
        if (!StringUtils.hasText(originalName) || originalName.length() > 255) {
            throw new BizException("文件名称不合法");
        }
        String ext = originalName.contains(".") ? originalName.substring(originalName.lastIndexOf('.') + 1).toLowerCase() : "bin";
        if (!("mp4".equals(ext) || "pdf".equals(ext) || "doc".equals(ext) || "docx".equals(ext) || "ppt".equals(ext) || "pptx".equals(ext))) {
            throw new BizException("仅支持视频/PDF/文档类型");
        }

        try {
            Path dir = Paths.get(uploadDir, "course", String.valueOf(courseId));
            Files.createDirectories(dir);
            String stored = UUID.randomUUID() + "_" + originalName;
            Path target = dir.resolve(stored);
            file.transferTo(target);
            String fileUrl = "/files/course/" + courseId + "/" + stored;

            CourseMaterial material = new CourseMaterial();
            material.setCourseId(courseId);
            material.setFileName(originalName);
            material.setFileUrl(fileUrl);
            material.setFileType(ext);
            material.setSize(file.getSize());
            materialMapper.insert(material);
            return fileUrl;
        } catch (IOException e) {
            throw new BizException("文件上传失败: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        if (materialMapper.logicDeleteById(id) == 0) {
            throw new BizException("课件不存在或已删除");
        }
    }

    @Override
    public List<CourseMaterial> listByCourseId(Long courseId) {
        return materialMapper.selectByCourseId(courseId);
    }
}
