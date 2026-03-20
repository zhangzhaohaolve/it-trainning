# IT 企业员工培训管理系统

## 运行环境
- JDK 17+
- Maven 3.9+
- Node.js 20+
- MySQL 8.0+

## 1. 初始化数据库
1. 创建数据库：`it_training`。
2. 执行脚本：`backend/src/main/resources/db/schema.sql`。
3. 默认初始化账号：
   - 用户名：`admin`
   - 密码：`123456`

## 2. 启动后端
```bash
cd backend
mvn clean package -DskipTests
mvn spring-boot:run
```

后端默认端口：`8080`

> 如需修改数据库账号密码，请编辑 `backend/src/main/resources/application.yml`。

## 3. 启动前端
```bash
cd frontend
npm install
npm run dev
```

前端默认端口：`5173`

## 4. 本地访问
- 前端：`http://localhost:5173`
- 后端接口前缀：`http://localhost:8080/api`

## 5. 常见问题
- 若出现跨域问题：已在后端 `WebMvcConfig` 中开放 CORS。
- 若上传课件 404：请确认后端 `app.upload-dir` 目录可写且已启动。
