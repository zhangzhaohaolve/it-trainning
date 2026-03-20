package com.company.training.modules.course.controller;

import com.company.training.common.result.Result;
import com.company.training.modules.course.dto.CourseCategoryAddDTO;
import com.company.training.modules.course.dto.CourseCategoryQueryDTO;
import com.company.training.modules.course.dto.CourseCategoryUpdateDTO;
import com.company.training.modules.course.entity.CourseCategory;
import com.company.training.modules.course.service.CourseCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course/category")
@RequiredArgsConstructor
public class CourseCategoryController {
    private final CourseCategoryService categoryService;

    @GetMapping("/list")
    public Result<List<CourseCategory>> list(CourseCategoryQueryDTO queryDTO) {
        return Result.success(categoryService.list(queryDTO));
    }

    @PostMapping
    public Result<Long> add(@RequestBody @Valid CourseCategoryAddDTO dto) {
        return Result.success(categoryService.add(dto));
    }

    @PutMapping
    public Result<Void> update(@RequestBody @Valid CourseCategoryUpdateDTO dto) {
        categoryService.update(dto);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return Result.success();
    }
}
