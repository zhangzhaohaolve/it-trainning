package com.company.training.modules.course.controller;

import com.company.training.common.result.Result;
import com.company.training.modules.course.dto.LecturerAddDTO;
import com.company.training.modules.course.dto.LecturerQueryDTO;
import com.company.training.modules.course.dto.LecturerUpdateDTO;
import com.company.training.modules.course.entity.Lecturer;
import com.company.training.modules.course.service.LecturerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course/lecturer")
@RequiredArgsConstructor
public class LecturerController {
    private final LecturerService lecturerService;

    @GetMapping("/list")
    public Result<List<Lecturer>> list(LecturerQueryDTO queryDTO) {
        return Result.success(lecturerService.list(queryDTO));
    }

    @PostMapping
    public Result<Long> add(@RequestBody @Valid LecturerAddDTO dto) {
        return Result.success(lecturerService.add(dto));
    }

    @PutMapping
    public Result<Void> update(@RequestBody @Valid LecturerUpdateDTO dto) {
        lecturerService.update(dto);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        lecturerService.delete(id);
        return Result.success();
    }
}
