package com.company.training.modules.course.controller;

import com.company.training.common.page.PageResult;
import com.company.training.common.result.Result;
import com.company.training.modules.course.dto.*;
import com.company.training.modules.course.entity.Course;
import com.company.training.modules.course.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
@Validated
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/page")
    public Result<PageResult<Course>> page(@Valid CourseQueryDTO queryDTO) {
        return Result.success(courseService.page(queryDTO));
    }

    @GetMapping("/{id}")
    public Result<CourseDetailVO> detail(@PathVariable Long id) {
        return Result.success(courseService.detail(id));
    }

    @PostMapping
    public Result<Long> add(@RequestBody @Valid CourseAddDTO dto) {
        return Result.success(courseService.add(dto));
    }

    @PutMapping
    public Result<Void> update(@RequestBody @Valid CourseUpdateDTO dto) {
        courseService.update(dto);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        courseService.delete(id);
        return Result.success();
    }
}
