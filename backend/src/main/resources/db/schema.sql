SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `sys_log`;
DROP TABLE IF EXISTS `makeup_exam`;
DROP TABLE IF EXISTS `exam_score`;
DROP TABLE IF EXISTS `exam`;
DROP TABLE IF EXISTS `exam_question_rel`;
DROP TABLE IF EXISTS `question`;
DROP TABLE IF EXISTS `learning_record`;
DROP TABLE IF EXISTS `training_plan_user`;
DROP TABLE IF EXISTS `training_plan_course`;
DROP TABLE IF EXISTS `training_plan`;
DROP TABLE IF EXISTS `course`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `dept`;

CREATE TABLE `dept` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dept_code` VARCHAR(64) NOT NULL COMMENT '部门编码',
  `dept_name` VARCHAR(128) NOT NULL COMMENT '部门名称',
  `parent_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级部门ID，0为根节点',
  `leader_user_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '部门负责人用户ID',
  `sort_no` INT NOT NULL DEFAULT 0 COMMENT '排序号',
  `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1启用 0停用',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删 1已删',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_dept_code` (`dept_code`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_leader_user_id` (`leader_user_id`),
  KEY `idx_dept_name` (`dept_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';

CREATE TABLE `user` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` VARCHAR(64) NOT NULL COMMENT '登录账号',
  `password` VARCHAR(255) NOT NULL COMMENT '密码(加密后)',
  `real_name` VARCHAR(64) NOT NULL COMMENT '真实姓名',
  `employee_no` VARCHAR(64) NOT NULL COMMENT '员工工号',
  `dept_id` BIGINT UNSIGNED NOT NULL COMMENT '部门ID，关联dept.id',
  `email` VARCHAR(128) DEFAULT NULL COMMENT '邮箱',
  `phone` VARCHAR(32) DEFAULT NULL COMMENT '手机号',
  `gender` TINYINT NOT NULL DEFAULT 0 COMMENT '性别：0未知 1男 2女',
  `position_name` VARCHAR(64) DEFAULT NULL COMMENT '岗位名称',
  `hire_date` DATE DEFAULT NULL COMMENT '入职日期',
  `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1在职/启用 0离职/禁用',
  `last_login_time` DATETIME DEFAULT NULL COMMENT '最后登录时间',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删 1已删',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  UNIQUE KEY `uk_employee_no` (`employee_no`),
  KEY `idx_dept_id` (`dept_id`),
  KEY `idx_real_name` (`real_name`),
  KEY `idx_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工表';

CREATE TABLE `course` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `course_code` VARCHAR(64) NOT NULL COMMENT '课程编码',
  `course_name` VARCHAR(128) NOT NULL COMMENT '课程名称',
  `course_type` VARCHAR(32) NOT NULL COMMENT '课程类型：online/offline/blended',
  `category` VARCHAR(64) DEFAULT NULL COMMENT '课程分类',
  `lecturer_user_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '讲师用户ID，关联user.id',
  `difficulty_level` TINYINT NOT NULL DEFAULT 1 COMMENT '难度等级：1初级 2中级 3高级',
  `credit` DECIMAL(6,2) NOT NULL DEFAULT 0.00 COMMENT '课程学分',
  `duration_minutes` INT NOT NULL DEFAULT 0 COMMENT '课程时长(分钟)',
  `description` TEXT COMMENT '课程描述',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态：0草稿 1已发布 2已下线',
  `create_by` BIGINT UNSIGNED DEFAULT NULL COMMENT '创建人ID',
  `update_by` BIGINT UNSIGNED DEFAULT NULL COMMENT '更新人ID',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删 1已删',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_course_code` (`course_code`),
  KEY `idx_course_name` (`course_name`),
  KEY `idx_course_type` (`course_type`),
  KEY `idx_lecturer_user_id` (`lecturer_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程表';

CREATE TABLE `training_plan` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `plan_code` VARCHAR(64) NOT NULL COMMENT '培训计划编码',
  `plan_name` VARCHAR(128) NOT NULL COMMENT '培训计划名称',
  `plan_year` SMALLINT NOT NULL COMMENT '计划年度',
  `plan_quarter` TINYINT DEFAULT NULL COMMENT '计划季度：1-4',
  `owner_user_id` BIGINT UNSIGNED NOT NULL COMMENT '计划负责人ID，关联user.id',
  `start_date` DATE NOT NULL COMMENT '计划开始日期',
  `end_date` DATE NOT NULL COMMENT '计划结束日期',
  `target_desc` VARCHAR(1000) DEFAULT NULL COMMENT '计划目标描述',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态：0草稿 1进行中 2已完成 3已取消',
  `approval_status` TINYINT NOT NULL DEFAULT 0 COMMENT '审批状态：0待审 1通过 2驳回',
  `create_by` BIGINT UNSIGNED DEFAULT NULL COMMENT '创建人ID',
  `update_by` BIGINT UNSIGNED DEFAULT NULL COMMENT '更新人ID',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删 1已删',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_plan_code` (`plan_code`),
  KEY `idx_owner_user_id` (`owner_user_id`),
  KEY `idx_plan_year_quarter` (`plan_year`, `plan_quarter`),
  KEY `idx_plan_name` (`plan_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训计划表';

CREATE TABLE `training_plan_course` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `plan_id` BIGINT UNSIGNED NOT NULL COMMENT '培训计划ID，关联training_plan.id',
  `course_id` BIGINT UNSIGNED NOT NULL COMMENT '课程ID，关联course.id',
  `required_flag` TINYINT NOT NULL DEFAULT 1 COMMENT '是否必修：1是 0否',
  `sort_no` INT NOT NULL DEFAULT 0 COMMENT '课程排序',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删 1已删',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_plan_course` (`plan_id`, `course_id`),
  KEY `idx_course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训计划-课程关联表';

CREATE TABLE `training_plan_user` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `plan_id` BIGINT UNSIGNED NOT NULL COMMENT '培训计划ID，关联training_plan.id',
  `user_id` BIGINT UNSIGNED NOT NULL COMMENT '员工ID，关联user.id',
  `dept_id` BIGINT UNSIGNED NOT NULL COMMENT '员工所属部门ID，关联dept.id',
  `mandatory_flag` TINYINT NOT NULL DEFAULT 1 COMMENT '是否强制参与：1是 0否',
  `assign_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '指派时间',
  `due_date` DATE DEFAULT NULL COMMENT '完成截止日期',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态：0未开始 1学习中 2已完成',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删 1已删',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_plan_user` (`plan_id`, `user_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_dept_id` (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='培训计划-员工关联表';

CREATE TABLE `learning_record` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `plan_id` BIGINT UNSIGNED NOT NULL COMMENT '培训计划ID，关联training_plan.id',
  `course_id` BIGINT UNSIGNED NOT NULL COMMENT '课程ID，关联course.id',
  `user_id` BIGINT UNSIGNED NOT NULL COMMENT '员工ID，关联user.id',
  `study_date` DATE NOT NULL COMMENT '学习日期',
  `study_minutes` INT NOT NULL DEFAULT 0 COMMENT '学习时长(分钟)',
  `progress_percent` DECIMAL(5,2) NOT NULL DEFAULT 0.00 COMMENT '课程进度百分比',
  `completion_status` TINYINT NOT NULL DEFAULT 0 COMMENT '完成状态：0未完成 1已完成',
  `last_position` VARCHAR(255) DEFAULT NULL COMMENT '学习位置标记(章节/时间点)',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删 1已删',
  PRIMARY KEY (`id`),
  KEY `idx_plan_id` (`plan_id`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_study_date` (`study_date`),
  KEY `idx_user_course` (`user_id`, `course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学习记录表';

CREATE TABLE `question` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `question_code` VARCHAR(64) NOT NULL COMMENT '题目编码',
  `question_type` TINYINT NOT NULL COMMENT '题型：1单选 2多选 3判断 4填空 5简答',
  `difficulty_level` TINYINT NOT NULL DEFAULT 1 COMMENT '难度等级：1简单 2中等 3困难',
  `course_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '所属课程ID，关联course.id',
  `knowledge_point` VARCHAR(255) DEFAULT NULL COMMENT '知识点',
  `title` VARCHAR(1000) NOT NULL COMMENT '题干',
  `options_json` JSON DEFAULT NULL COMMENT '选项JSON',
  `answer_json` JSON NOT NULL COMMENT '标准答案JSON',
  `analysis_text` VARCHAR(2000) DEFAULT NULL COMMENT '题目解析',
  `score` DECIMAL(6,2) NOT NULL DEFAULT 0.00 COMMENT '默认分值',
  `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1启用 0停用',
  `create_by` BIGINT UNSIGNED DEFAULT NULL COMMENT '创建人ID',
  `update_by` BIGINT UNSIGNED DEFAULT NULL COMMENT '更新人ID',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删 1已删',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_question_code` (`question_code`),
  KEY `idx_question_type` (`question_type`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_difficulty_level` (`difficulty_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='题库表';

CREATE TABLE `exam` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `exam_code` VARCHAR(64) NOT NULL COMMENT '试卷/考试编码',
  `exam_name` VARCHAR(128) NOT NULL COMMENT '试卷/考试名称',
  `exam_type` TINYINT NOT NULL DEFAULT 1 COMMENT '考试类型：1正式考试 2练习',
  `plan_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '关联培训计划ID，training_plan.id',
  `course_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '关联课程ID，course.id',
  `duration_minutes` INT NOT NULL COMMENT '考试时长(分钟)',
  `total_score` DECIMAL(6,2) NOT NULL COMMENT '总分',
  `pass_score` DECIMAL(6,2) NOT NULL COMMENT '及格分',
  `start_time` DATETIME NOT NULL COMMENT '考试开始时间',
  `end_time` DATETIME NOT NULL COMMENT '考试结束时间',
  `publish_status` TINYINT NOT NULL DEFAULT 0 COMMENT '发布状态：0未发布 1已发布',
  `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1有效 0失效',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `create_by` BIGINT UNSIGNED DEFAULT NULL COMMENT '创建人ID',
  `update_by` BIGINT UNSIGNED DEFAULT NULL COMMENT '更新人ID',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删 1已删',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_exam_code` (`exam_code`),
  KEY `idx_plan_id` (`plan_id`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_start_end_time` (`start_time`, `end_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='试卷/考试表';

CREATE TABLE `exam_question_rel` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `exam_id` BIGINT UNSIGNED NOT NULL COMMENT '考试ID，关联exam.id',
  `question_id` BIGINT UNSIGNED NOT NULL COMMENT '题目ID，关联question.id',
  `question_no` INT NOT NULL COMMENT '题号',
  `score` DECIMAL(6,2) NOT NULL DEFAULT 0.00 COMMENT '该题分值',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删 1已删',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_exam_question` (`exam_id`, `question_id`),
  UNIQUE KEY `uk_exam_question_no` (`exam_id`, `question_no`),
  KEY `idx_question_id` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='试卷题目关联表';

CREATE TABLE `exam_score` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `exam_id` BIGINT UNSIGNED NOT NULL COMMENT '考试ID，关联exam.id',
  `user_id` BIGINT UNSIGNED NOT NULL COMMENT '员工ID，关联user.id',
  `attempt_no` INT NOT NULL DEFAULT 1 COMMENT '作答次数',
  `objective_score` DECIMAL(6,2) NOT NULL DEFAULT 0.00 COMMENT '客观题得分',
  `subjective_score` DECIMAL(6,2) NOT NULL DEFAULT 0.00 COMMENT '主观题得分',
  `final_score` DECIMAL(6,2) NOT NULL DEFAULT 0.00 COMMENT '最终成绩',
  `pass_flag` TINYINT NOT NULL DEFAULT 0 COMMENT '是否通过：0否 1是',
  `submit_time` DATETIME DEFAULT NULL COMMENT '交卷时间',
  `grader_user_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '阅卷人ID，关联user.id',
  `grade_time` DATETIME DEFAULT NULL COMMENT '阅卷时间',
  `score_status` TINYINT NOT NULL DEFAULT 0 COMMENT '成绩状态：0待阅卷 1已发布',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删 1已删',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_exam_user_attempt` (`exam_id`, `user_id`, `attempt_no`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_submit_time` (`submit_time`),
  KEY `idx_pass_flag` (`pass_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考试成绩表';

CREATE TABLE `makeup_exam` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `exam_id` BIGINT UNSIGNED NOT NULL COMMENT '原考试ID，关联exam.id',
  `user_id` BIGINT UNSIGNED NOT NULL COMMENT '申请补考员工ID，关联user.id',
  `source_score_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '原成绩ID，关联exam_score.id',
  `apply_reason` VARCHAR(500) DEFAULT NULL COMMENT '补考申请原因',
  `approval_status` TINYINT NOT NULL DEFAULT 0 COMMENT '审批状态：0待审 1通过 2驳回',
  `approver_user_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '审批人ID，关联user.id',
  `approval_time` DATETIME DEFAULT NULL COMMENT '审批时间',
  `makeup_start_time` DATETIME DEFAULT NULL COMMENT '补考开始时间',
  `makeup_end_time` DATETIME DEFAULT NULL COMMENT '补考结束时间',
  `makeup_exam_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '补考对应exam.id',
  `makeup_score_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '补考成绩ID，关联exam_score.id',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态：0已申请 1已安排 2已完成 3已取消',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删 1已删',
  PRIMARY KEY (`id`),
  KEY `idx_exam_id` (`exam_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_approval_status` (`approval_status`),
  KEY `idx_makeup_exam_id` (`makeup_exam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='补考表';

CREATE TABLE `sys_log` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `log_type` TINYINT NOT NULL COMMENT '日志类型：1登录日志 2操作日志 3异常日志',
  `module_name` VARCHAR(64) DEFAULT NULL COMMENT '模块名称',
  `operation_name` VARCHAR(128) DEFAULT NULL COMMENT '操作名称',
  `request_uri` VARCHAR(255) DEFAULT NULL COMMENT '请求URI',
  `request_method` VARCHAR(16) DEFAULT NULL COMMENT '请求方法',
  `request_params` TEXT COMMENT '请求参数',
  `response_data` TEXT COMMENT '响应数据',
  `operator_user_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '操作人ID，关联user.id',
  `operator_name` VARCHAR(64) DEFAULT NULL COMMENT '操作人名称',
  `operator_ip` VARCHAR(64) DEFAULT NULL COMMENT '操作IP',
  `user_agent` VARCHAR(500) DEFAULT NULL COMMENT '用户代理',
  `cost_ms` INT NOT NULL DEFAULT 0 COMMENT '耗时(毫秒)',
  `result_code` VARCHAR(32) DEFAULT NULL COMMENT '结果码',
  `result_msg` VARCHAR(500) DEFAULT NULL COMMENT '结果描述',
  `biz_id` VARCHAR(64) DEFAULT NULL COMMENT '业务ID(可存订单/计划等ID)',
  `biz_type` VARCHAR(32) DEFAULT NULL COMMENT '业务类型',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删 1已删',
  PRIMARY KEY (`id`),
  KEY `idx_log_type` (`log_type`),
  KEY `idx_operator_user_id` (`operator_user_id`),
  KEY `idx_module_operation` (`module_name`, `operation_name`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统日志表';

SET FOREIGN_KEY_CHECKS = 1;
