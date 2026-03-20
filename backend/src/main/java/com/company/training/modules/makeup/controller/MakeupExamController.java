package com.company.training.modules.makeup.controller;

import com.company.training.common.page.PageResult;
import com.company.training.common.result.Result;
import com.company.training.modules.makeup.dto.MakeupExamQueryDTO;
import com.company.training.modules.makeup.entity.MakeupExam;
import com.company.training.modules.makeup.service.MakeupExamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/makeup/exam")
@RequiredArgsConstructor
public class MakeupExamController {

    private final MakeupExamService makeupExamService;

    @PostMapping("/simulateAutoGenerate")
    public Result<Void> simulateAutoGenerate(@RequestParam Long examId, @RequestParam Long userId, @RequestParam Integer score, @RequestParam Integer passScore) {
        makeupExamService.simulateAutoGenerate(examId, userId, score, passScore);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<PageResult<MakeupExam>> list(@Valid MakeupExamQueryDTO dto) {
        return Result.success(makeupExamService.list(dto));
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        return Result.fail("补考任务禁止删除");
    }
}
