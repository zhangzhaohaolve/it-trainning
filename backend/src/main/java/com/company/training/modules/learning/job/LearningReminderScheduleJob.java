package com.company.training.modules.learning.job;

import com.company.training.modules.learning.dto.LearningReminderAddDTO;
import com.company.training.modules.learning.service.LearningReminderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LearningReminderScheduleJob {

    private final LearningReminderService reminderService;

    @Scheduled(cron = "0 0 9 * * ?")
    public void scanAndRemind() {
        log.info("start learning reminder scan");
        // 示例：实际应扫描未完成/临近截止/进度落后人员。
        LearningReminderAddDTO dto = new LearningReminderAddDTO();
        dto.setUserId(1L);
        dto.setCourseId(1L);
        dto.setReminderType(3);
        dto.setContent("课程即将截止，请尽快完成学习");
        reminderService.add(dto);
        log.info("learning reminder scan completed");
    }
}
