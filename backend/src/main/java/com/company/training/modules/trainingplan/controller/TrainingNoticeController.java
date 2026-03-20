package com.company.training.modules.trainingplan.controller;

import com.company.training.common.result.Result;
import com.company.training.modules.trainingplan.dto.TrainingNoticeQueryDTO;
import com.company.training.modules.trainingplan.dto.TrainingNoticeReadDTO;
import com.company.training.modules.trainingplan.dto.TrainingNoticeSendDTO;
import com.company.training.modules.trainingplan.entity.TrainingNotice;
import com.company.training.modules.trainingplan.service.TrainingNoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training-plan/notice")
@RequiredArgsConstructor
public class TrainingNoticeController {

    private final TrainingNoticeService noticeService;

    @PostMapping("/manual-send")
    public Result<Void> manualSend(@RequestBody @Valid TrainingNoticeSendDTO dto) {
        noticeService.manualSend(dto);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<TrainingNotice>> list(TrainingNoticeQueryDTO queryDTO) {
        return Result.success(noticeService.list(queryDTO));
    }

    @PutMapping("/read-status")
    public Result<Void> updateReadStatus(@RequestBody @Valid TrainingNoticeReadDTO dto) {
        noticeService.setReadStatus(dto);
        return Result.success();
    }
}
