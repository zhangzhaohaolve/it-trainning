CREATE TABLE IF NOT EXISTS makeup_exam (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  exam_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  reason VARCHAR(255) NOT NULL DEFAULT '考试不及格',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status TINYINT NOT NULL DEFAULT 0 COMMENT '0待补考 1已完成',
  deleted TINYINT NOT NULL DEFAULT 0,
  KEY idx_exam_user (exam_id, user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='补考任务';

CREATE TABLE IF NOT EXISTS makeup_schedule (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  makeup_id BIGINT NOT NULL,
  makeup_time DATETIME NOT NULL,
  location VARCHAR(255) NOT NULL,
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  deleted TINYINT NOT NULL DEFAULT 0,
  KEY idx_makeup_time (makeup_time),
  KEY idx_makeup_id (makeup_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='补考安排';

CREATE TABLE IF NOT EXISTS makeup_score (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  makeup_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  answers JSON NOT NULL,
  score INT NOT NULL DEFAULT 0,
  submit_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status TINYINT NOT NULL DEFAULT 0 COMMENT '0未判完 1已完成',
  reviewer BIGINT NULL,
  review_time DATETIME NULL,
  deleted TINYINT NOT NULL DEFAULT 0,
  KEY idx_makeup_id (makeup_id),
  KEY idx_user_id (user_id),
  UNIQUE KEY uk_makeup_user (makeup_id, user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='补考成绩';
