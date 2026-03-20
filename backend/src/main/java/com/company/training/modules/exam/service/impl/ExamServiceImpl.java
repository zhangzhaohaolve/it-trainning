package com.company.training.modules.exam.service.impl;

import com.company.training.common.exception.BizException;
import com.company.training.common.page.PageResult;
import com.company.training.modules.exam.dto.*;
import com.company.training.modules.exam.entity.*;
import com.company.training.modules.exam.mapper.*;
import com.company.training.modules.exam.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamMapper examMapper;
    private final QuestionMapper questionMapper;
    private final ExamQuestionMapper examQuestionMapper;
    private final ExamScoreMapper examScoreMapper;

    @Override
    public PageResult<Exam> list(ExamQueryDTO dto) {
        return new PageResult<>(examMapper.countPage(dto), examMapper.selectPage(dto));
    }

    @Override
    public Long add(ExamAddDTO dto) {
        Exam e = new Exam();
        e.setCourseId(dto.getCourseId()); e.setTitle(dto.getTitle()); e.setDuration(dto.getDuration()); e.setTotalScore(dto.getTotalScore()); e.setPassScore(dto.getPassScore()); e.setStartTime(dto.getStartTime()); e.setEndTime(dto.getEndTime()); e.setStatus(0);
        examMapper.insert(e);
        return e.getId();
    }

    @Override
    public void update(ExamUpdateDTO dto) {
        Exam e = examMapper.selectById(dto.getId());
        if (e == null) throw new BizException("考试不存在");
        e.setCourseId(dto.getCourseId()); e.setTitle(dto.getTitle()); e.setDuration(dto.getDuration()); e.setTotalScore(dto.getTotalScore()); e.setPassScore(dto.getPassScore()); e.setStartTime(dto.getStartTime()); e.setEndTime(dto.getEndTime());
        examMapper.updateById(e);
    }

    @Override
    public void delete(Long id) { examMapper.logicDeleteById(id); }

    @Override
    public void publish(Long id) { examMapper.publish(id); }

    @Override
    public void autoGenerateExamPaper(ExamGenerateDTO dto) {
        Exam exam = examMapper.selectById(dto.getExamId());
        if (exam == null) throw new BizException("考试不存在");
        examQuestionMapper.deleteByExamId(dto.getExamId());
        List<Question> singles = questionMapper.randomByTypeAndDifficulty(exam.getCourseId(), 1, dto.getDifficulty(), dto.getSingleCount());
        List<Question> judges = questionMapper.randomByTypeAndDifficulty(exam.getCourseId(), 2, dto.getDifficulty(), dto.getJudgeCount());
        List<Question> shorts = questionMapper.randomByTypeAndDifficulty(exam.getCourseId(), 3, dto.getDifficulty(), dto.getShortCount());
        List<Question> all = new ArrayList<>(); all.addAll(singles); all.addAll(judges); all.addAll(shorts);
        int eachScore = all.isEmpty() ? 0 : exam.getTotalScore() / all.size();
        int sort = 1;
        for (Question q : all) {
            ExamQuestion eq = new ExamQuestion();
            eq.setExamId(dto.getExamId()); eq.setQuestionId(q.getId()); eq.setScore(eachScore); eq.setSort(sort++);
            examQuestionMapper.insert(eq);
        }
    }

    @Override
    public List<ExamQuestion> detailQuestions(Long examId) {
        Exam exam = examMapper.selectById(examId);
        if (exam == null) throw new BizException("考试不存在");
        if (LocalDateTime.now().isAfter(exam.getEndTime())) throw new BizException("考试已过期");
        return examQuestionMapper.selectByExamId(examId);
    }

    @Override
    public Long submitExam(SubmitExamDTO dto) {
        Exam exam = examMapper.selectById(dto.getExamId());
        if (exam == null) throw new BizException("考试不存在");
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(exam.getStartTime()) || now.isAfter(exam.getEndTime())) throw new BizException("不在考试时间范围内");
        if (examScoreMapper.selectByExamAndUser(dto.getExamId(), dto.getUserId()) != null) throw new BizException("不可重复提交");

        int autoScore = autoJudge(dto.getExamId(), dto.getAnswers());
        ExamScore s = new ExamScore();
        s.setExamId(dto.getExamId()); s.setUserId(dto.getUserId()); s.setAnswers(dto.getAnswers()); s.setScore(autoScore); s.setStatus(0);
        examScoreMapper.insert(s);
        return s.getId();
    }

    private int autoJudge(Long examId, String answersJson) {
        List<ExamQuestion> eqs = examQuestionMapper.selectByExamId(examId);
        if (answersJson == null) return 0;
        Map<String, Object> ans = new HashMap<>();
        try {
            // 轻量实现：{"questionId":"answer"}
            String body = answersJson.trim().replaceAll("[{}\" ]", "");
            if (!body.isEmpty()) {
                for (String kv : body.split(",")) {
                    String[] p = kv.split(":");
                    if (p.length == 2) ans.put(p[0], p[1]);
                }
            }
        } catch (Exception ignored) {}
        int score = 0;
        for (ExamQuestion eq : eqs) {
            Question q = questionMapper.selectById(eq.getQuestionId());
            if (q == null || q.getType() == 3) continue;
            Object ua = ans.get(String.valueOf(q.getId()));
            if (ua != null && q.getAnswer() != null && q.getAnswer().equalsIgnoreCase(String.valueOf(ua))) score += eq.getScore();
        }
        return score;
    }
}
