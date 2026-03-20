# IT 企业员工培训管理系统项目框架（Spring Boot 3 + Vue3）

## 1. 后端项目目录结构（Spring Boot + MyBatis）

```text
backend
├─ pom.xml
└─ src
   └─ main
      ├─ java/com/company/training
      │  ├─ TrainingApplication.java
      │  ├─ common
      │  │  ├─ annotation
      │  │  ├─ constant
      │  │  ├─ enums
      │  │  ├─ exception
      │  │  ├─ model
      │  │  │  ├─ dto
      │  │  │  ├─ vo
      │  │  │  └─ entity
      │  │  └─ result
      │  ├─ config
      │  │  ├─ MyBatisConfig.java
      │  │  ├─ SecurityConfig.java
      │  │  └─ SwaggerConfig.java
      │  ├─ auth
      │  │  ├─ JwtTokenProvider.java
      │  │  ├─ JwtAuthenticationFilter.java
      │  │  ├─ LoginController.java
      │  │  └─ AuthService.java
      │  └─ modules
      │     ├─ course
      │     ├─ plan
      │     ├─ learning
      │     ├─ exam
      │     ├─ retake
      │     ├─ statistics
      │     └─ system
      │        ├─ user
      │        ├─ role
      │        ├─ dept
      │        └─ log
      └─ resources
         ├─ application.yml
         ├─ db
         │  └─ schema.sql
         └─ mapper
            ├─ course
            ├─ plan
            ├─ learning
            ├─ exam
            ├─ retake
            ├─ statistics
            └─ system
```

> 模块包内建议统一结构：`controller / service / service.impl / mapper / entity / dto / vo`。

## 2. 前端项目目录结构（Vue3 + Vite + ElementPlus）

```text
frontend
├─ package.json
├─ vite.config.js
└─ src
   ├─ main.js
   ├─ App.vue
   ├─ api
   │  ├─ http.js
   │  ├─ auth.js
   │  ├─ course.js
   │  ├─ plan.js
   │  ├─ learning.js
   │  ├─ exam.js
   │  ├─ retake.js
   │  ├─ statistics.js
   │  └─ system
   │     ├─ user.js
   │     ├─ role.js
   │     ├─ dept.js
   │     └─ log.js
   ├─ router
   │  └─ index.js
   ├─ store
   │  ├─ auth.js
   │  ├─ permission.js
   │  └─ app.js
   ├─ layout
   │  ├─ index.vue
   │  ├─ Sidebar.vue
   │  ├─ Navbar.vue
   │  └─ AppMain.vue
   ├─ views
   │  ├─ course
   │  ├─ plan
   │  ├─ learning
   │  ├─ exam
   │  ├─ retake
   │  ├─ statistics
   │  └─ system
   │     ├─ user
   │     ├─ role
   │     ├─ dept
   │     └─ log
   ├─ components
   │  ├─ table
   │  ├─ form
   │  ├─ upload
   │  └─ chart
   ├─ utils
   │  ├─ auth.js
   │  ├─ request.js
   │  └─ permission.js
   └─ styles
      ├─ index.scss
      └─ variables.scss
```

## 3. 数据库总表结构（简表）

### 3.1 课程管理

| 表名 | 说明 | 核心字段 |
|---|---|---|
| `t_course` | 课程主表 | id, code, name, category, lecturer_id, hours, difficulty, status, publish_time |
| `t_course_chapter` | 课程章节 | id, course_id, title, sort_no, content_type, content_url, duration |
| `t_course_enroll` | 课程报名/指派 | id, course_id, user_id, source_type, assign_time, deadline, status |

### 3.2 培训计划管理

| 表名 | 说明 | 核心字段 |
|---|---|---|
| `t_training_plan` | 培训计划 | id, plan_no, plan_name, year, quarter, owner_id, start_date, end_date, status |
| `t_plan_course` | 计划关联课程 | id, plan_id, course_id, required_flag, exam_required |
| `t_plan_participant` | 计划参与人 | id, plan_id, user_id, dept_id, mandatory_flag, progress |

### 3.3 学习管理

| 表名 | 说明 | 核心字段 |
|---|---|---|
| `t_learning_task` | 学习任务 | id, course_id, user_id, plan_id, start_time, end_time, progress, status |
| `t_learning_record` | 学习记录 | id, task_id, chapter_id, study_seconds, last_position, finished_flag, update_time |
| `t_learning_signin` | 学习签到 | id, task_id, user_id, sign_type, sign_time, location |

### 3.4 在线考试管理

| 表名 | 说明 | 核心字段 |
|---|---|---|
| `t_exam` | 考试主表 | id, exam_no, exam_name, course_id, duration, total_score, pass_score, start_time, end_time, status |
| `t_exam_question` | 试题池 | id, type, stem, options_json, answer_json, score, difficulty, status |
| `t_exam_paper` | 试卷 | id, exam_id, paper_name, rule_json, total_score |
| `t_exam_attempt` | 考试作答 | id, exam_id, paper_id, user_id, submit_time, objective_score, subjective_score, final_score, pass_flag |
| `t_exam_answer` | 作答明细 | id, attempt_id, question_id, user_answer, is_correct, score |

### 3.5 补考管理

| 表名 | 说明 | 核心字段 |
|---|---|---|
| `t_retake_apply` | 补考申请 | id, exam_id, user_id, reason, apply_time, approval_status, approver_id |
| `t_retake_schedule` | 补考安排 | id, exam_id, user_id, retake_time, duration, location, status |
| `t_retake_result` | 补考结果 | id, schedule_id, attempt_id, score, pass_flag, publish_time |

### 3.6 统计分析

| 表名 | 说明 | 核心字段 |
|---|---|---|
| `t_stat_course` | 课程统计快照 | id, stat_date, course_id, learned_count, completed_count, completion_rate |
| `t_stat_exam` | 考试统计快照 | id, stat_date, exam_id, attend_count, pass_count, pass_rate, avg_score |
| `t_stat_dept` | 部门培训统计 | id, stat_date, dept_id, plan_count, completion_rate, pass_rate |

### 3.7 系统管理（RBAC + 日志）

| 表名 | 说明 | 核心字段 |
|---|---|---|
| `sys_user` | 用户 | id, username, password, real_name, email, phone, dept_id, status, last_login_time |
| `sys_role` | 角色 | id, role_code, role_name, data_scope, status |
| `sys_user_role` | 用户角色关联 | id, user_id, role_id |
| `sys_dept` | 部门 | id, parent_id, dept_code, dept_name, leader_id, status |
| `sys_permission` | 权限点 | id, perm_code, perm_name, perm_type, path, method |
| `sys_role_permission` | 角色权限关联 | id, role_id, permission_id |
| `sys_operation_log` | 操作日志 | id, module, operation, request_uri, request_method, operator_id, cost_ms, result, created_time |
| `sys_login_log` | 登录日志 | id, user_id, login_ip, login_location, user_agent, login_status, created_time |

## 4. 必要依赖

### 4.1 后端 `pom.xml`（核心）

- Spring Boot Web / Validation / Security
- MyBatis Spring Boot Starter
- MySQL Driver
- JWT（jjwt-api / jjwt-impl / jjwt-jackson）
- Lombok
- Spring Boot Test

### 4.2 前端 `package.json`（核心）

- vue / vue-router / pinia
- element-plus
- axios
- dayjs
- vite / @vitejs/plugin-vue
- eslint / eslint-plugin-vue / sass

## 5. API 规划表（RESTful）

统一前缀：`/api/v1`

### 5.1 认证与权限

| 模块 | 方法 | 路径 | 说明 |
|---|---|---|---|
| 认证 | POST | `/auth/login` | 账号登录，返回 JWT |
| 认证 | POST | `/auth/logout` | 退出登录 |
| 认证 | GET | `/auth/me` | 当前用户信息/权限 |
| 权限 | GET | `/auth/menus` | 当前用户菜单树 |

### 5.2 课程管理

| 方法 | 路径 | 说明 |
|---|---|---|
| GET | `/courses` | 课程分页查询 |
| POST | `/courses` | 新增课程 |
| GET | `/courses/{id}` | 课程详情 |
| PUT | `/courses/{id}` | 更新课程 |
| DELETE | `/courses/{id}` | 删除课程 |
| POST | `/courses/{id}/publish` | 发布课程 |
| GET | `/courses/{id}/chapters` | 章节列表 |
| POST | `/courses/{id}/enrollments` | 指派学习人员 |

### 5.3 培训计划管理

| 方法 | 路径 | 说明 |
|---|---|---|
| GET | `/plans` | 计划分页查询 |
| POST | `/plans` | 新增计划 |
| GET | `/plans/{id}` | 计划详情 |
| PUT | `/plans/{id}` | 更新计划 |
| DELETE | `/plans/{id}` | 删除计划 |
| POST | `/plans/{id}/courses` | 批量关联课程 |
| POST | `/plans/{id}/participants` | 批量添加参与人 |
| POST | `/plans/{id}/publish` | 发布培训计划 |

### 5.4 学习管理

| 方法 | 路径 | 说明 |
|---|---|---|
| GET | `/learning/tasks` | 学习任务列表 |
| GET | `/learning/tasks/{id}` | 学习任务详情 |
| POST | `/learning/tasks/{id}/start` | 开始学习 |
| POST | `/learning/tasks/{id}/progress` | 上报学习进度 |
| POST | `/learning/tasks/{id}/finish` | 完成学习 |
| GET | `/learning/records` | 学习记录查询 |

### 5.5 在线考试管理

| 方法 | 路径 | 说明 |
|---|---|---|
| GET | `/exams` | 考试列表 |
| POST | `/exams` | 新增考试 |
| GET | `/exams/{id}` | 考试详情 |
| PUT | `/exams/{id}` | 更新考试 |
| DELETE | `/exams/{id}` | 删除考试 |
| POST | `/exams/{id}/publish` | 发布考试 |
| POST | `/exams/{id}/start` | 开始作答 |
| POST | `/exams/{id}/submit` | 提交试卷 |
| GET | `/exams/{id}/results` | 考试结果 |

### 5.6 补考管理

| 方法 | 路径 | 说明 |
|---|---|---|
| GET | `/retakes/applications` | 补考申请列表 |
| POST | `/retakes/applications` | 提交补考申请 |
| PUT | `/retakes/applications/{id}/approve` | 审批补考申请 |
| POST | `/retakes/schedules` | 生成补考安排 |
| GET | `/retakes/schedules` | 补考安排列表 |
| GET | `/retakes/results` | 补考结果列表 |

### 5.7 统计分析

| 方法 | 路径 | 说明 |
|---|---|---|
| GET | `/stats/dashboard` | 首页看板数据 |
| GET | `/stats/courses` | 课程统计 |
| GET | `/stats/exams` | 考试统计 |
| GET | `/stats/depts` | 部门统计 |
| GET | `/stats/export` | 统计结果导出 |

### 5.8 系统管理

| 方法 | 路径 | 说明 |
|---|---|---|
| GET | `/system/users` | 用户分页查询 |
| POST | `/system/users` | 新增用户 |
| PUT | `/system/users/{id}` | 修改用户 |
| DELETE | `/system/users/{id}` | 删除用户 |
| PUT | `/system/users/{id}/reset-password` | 重置密码 |
| GET | `/system/roles` | 角色列表 |
| POST | `/system/roles` | 新增角色 |
| PUT | `/system/roles/{id}` | 修改角色 |
| GET | `/system/depts/tree` | 部门树查询 |
| POST | `/system/depts` | 新增部门 |
| GET | `/system/logs/operation` | 操作日志 |
| GET | `/system/logs/login` | 登录日志 |

## 6. 命名与工程规范（建议）

- 接口命名：资源名复数 + REST 动词语义。
- DTO 后缀：`CreateReq / UpdateReq / QueryReq`。
- VO 后缀：`ListVO / DetailVO`。
- MyBatis Mapper 方法：`selectPage / selectById / insert / updateById / deleteById`。
- 统一返回：`{ code, message, data }`。
- 错误码：按模块分段，例如 `COURSE_4xxx`, `EXAM_5xxx`。

