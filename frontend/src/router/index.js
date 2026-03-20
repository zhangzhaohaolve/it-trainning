import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/course',
    name: 'CourseRoot',
    redirect: '/course/course/list',
    meta: { title: '课程管理', roles: ['admin', 'trainer'] },
    children: [
      { path: '/course/course/list', name: 'CourseList', component: () => import('@/views/course/course/index.vue'), meta: { title: '课程管理', roles: ['course:list'] } },
      { path: '/course/category', name: 'CourseCategory', component: () => import('@/views/course/category/index.vue'), meta: { title: '课程分类', roles: ['courseCategory:list'] } },
      { path: '/course/material', name: 'CourseMaterial', component: () => import('@/views/course/material/index.vue'), meta: { title: '课件管理', roles: ['courseMaterial:list'] } },
      { path: '/course/lecturer', name: 'CourseLecturer', component: () => import('@/views/course/lecturer/index.vue'), meta: { title: '讲师管理', roles: ['lecturer:list'] } }
    ]
  },
  {
    path: '/training-plan',
    name: 'TrainingPlanRoot',
    redirect: '/training-plan/list',
    meta: { title: '培训计划管理', roles: ['admin', 'trainer'] },
    children: [
      { path: '/training-plan/list', name: 'TrainingPlanList', component: () => import('@/views/training-plan/plan/index.vue'), meta: { title: '培训计划', roles: ['trainingPlan:list'] } },
      { path: '/training-plan/assign', name: 'TrainingPlanAssign', component: () => import('@/views/training-plan/assign/index.vue'), meta: { title: '任务分配', roles: ['trainingAssign:assign'] } },
      { path: '/training-plan/notice', name: 'TrainingPlanNotice', component: () => import('@/views/training-plan/notice/index.vue'), meta: { title: '通知推送', roles: ['trainingNotice:list'] } }
    ]
  }
,
  {
    path: '/learning',
    name: 'LearningRoot',
    redirect: '/learning/my',
    meta: { title: '学习管理', roles: ['admin', 'employee'] },
    children: [
      { path: '/learning/my', name: 'LearningMy', component: () => import('@/views/learning/my/index.vue'), meta: { title: '我的学习', roles: ['learningMy:list'] } },
      { path: '/learning/progress', name: 'LearningProgress', component: () => import('@/views/learning/progress/index.vue'), meta: { title: '学习进度', roles: ['learningProgress:list'] } },
      { path: '/learning/reminder', name: 'LearningReminder', component: () => import('@/views/learning/reminder/index.vue'), meta: { title: '学习提醒', roles: ['learningReminder:list'] } },
      { path: '/learning/record', name: 'LearningRecord', component: () => import('@/views/learning/record/index.vue'), meta: { title: '学习档案', roles: ['learningRecord:list'] } }
    ]
  }
,
  {
    path: '/exam',
    name: 'ExamRoot',
    redirect: '/exam/question',
    meta: { title: '在线考试', roles: ['admin', 'teacher', 'student'] },
    children: [
      { path: '/exam/question', name: 'ExamQuestion', component: () => import('@/views/exam/question.vue'), meta: { title: '题库管理', roles: ['question:list'] } },
      { path: '/exam/exam', name: 'ExamManage', component: () => import('@/views/exam/exam.vue'), meta: { title: '考试管理', roles: ['exam:list'] } },
      { path: '/exam/take', name: 'ExamTake', component: () => import('@/views/exam/take-exam.vue'), meta: { title: '在线考试', roles: ['exam:take'] } },
      { path: '/exam/review', name: 'ExamReview', component: () => import('@/views/exam/review.vue'), meta: { title: '判卷管理', roles: ['review:list'] } },
      { path: '/exam/score', name: 'ExamScore', component: () => import('@/views/exam/score.vue'), meta: { title: '成绩管理', roles: ['score:list'] } }
    ]
  }
,
  {
    path: '/makeup',
    name: 'MakeupRoot',
    redirect: '/makeup/exam',
    meta: { title: '补考管理', roles: ['admin', 'teacher'] },
    children: [
      { path: '/makeup/exam', name: 'MakeupExam', component: () => import('@/views/makeup/exam.vue'), meta: { title: '补考任务', roles: ['makeupExam:list'] } },
      { path: '/makeup/schedule', name: 'MakeupSchedule', component: () => import('@/views/makeup/schedule.vue'), meta: { title: '补考安排', roles: ['makeupSchedule:update'] } },
      { path: '/makeup/score', name: 'MakeupScore', component: () => import('@/views/makeup/score.vue'), meta: { title: '补考成绩', roles: ['makeupScore:list'] } }
    ]
  }
,
  {
    path: '/statistics/index',
    name: 'StatisticsIndex',
    component: () => import('@/views/statistics/index.vue'),
    meta: { title: '统计分析', roles: ['statistics:participate', 'statistics:completion', 'statistics:passrate'] }
  }
];

export default createRouter({ history: createWebHistory(), routes });
