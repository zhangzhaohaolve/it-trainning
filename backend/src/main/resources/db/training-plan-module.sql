CREATE TABLE IF NOT EXISTS training_plan (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL COMMENT '计划标题',
  description TEXT COMMENT '计划描述',
  start_time DATETIME NOT NULL COMMENT '开始时间',
  end_time DATETIME NOT NULL COMMENT '结束时间',
  status TINYINT NOT NULL DEFAULT 0 COMMENT '0未开始 1进行中 2已结束',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  deleted TINYINT NOT NULL DEFAULT 0,
  KEY idx_start_time (start_time),
  KEY idx_end_time (end_time),
  KEY idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='培训计划表';

CREATE TABLE IF NOT EXISTS training_plan_assign (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  plan_id BIGINT NOT NULL COMMENT '计划ID',
  user_id BIGINT NOT NULL COMMENT '用户ID',
  status TINYINT NOT NULL DEFAULT 0 COMMENT '0未完成 1已完成',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  deleted TINYINT NOT NULL DEFAULT 0,
  UNIQUE KEY uk_plan_user (plan_id, user_id),
  KEY idx_plan_id (plan_id),
  KEY idx_user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='培训任务分配表';

CREATE TABLE IF NOT EXISTS training_notice (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  plan_id BIGINT NOT NULL COMMENT '计划ID',
  user_id BIGINT NOT NULL COMMENT '用户ID',
  type TINYINT NOT NULL COMMENT '0系统自动 1管理员手动',
  content VARCHAR(1000) NOT NULL COMMENT '通知内容',
  send_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status TINYINT NOT NULL DEFAULT 0 COMMENT '0未读 1已读',
  deleted TINYINT NOT NULL DEFAULT 0,
  KEY idx_plan_id (plan_id),
  KEY idx_user_id (user_id),
  KEY idx_send_time (send_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='培训通知表';
