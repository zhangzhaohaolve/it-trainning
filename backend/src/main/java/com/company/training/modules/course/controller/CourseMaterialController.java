package com.company.training.modules.course.controller;

import com.company.training.common.result.Result;
import com.company.training.modules.course.entity.CourseMaterial;
import com.company.training.modules.course.service.CourseMaterialService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/course/material")
@RequiredArgsConstructor
@Validated
public class CourseMaterialController {
    private final CourseMaterialService materialService;

    @PostMapping("/upload")
    public Result<Map<String, String>> upload(@RequestParam @NotNull Long courseId,
                                              @RequestParam("file") MultipartFile file) {
        String fileUrl = materialService.upload(courseId, file);
        Map<String, String> data = new HashMap<>();
        data.put("fileUrl", fileUrl);
        return Result.success(data);
    }

    @GetMapping("/list")
    public Result<List<CourseMaterial>> list(@RequestParam @NotNull Long courseId) {
        return Result.success(materialService.listByCourseId(courseId));
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        materialService.delete(id);
        return Result.success();
    }
}
