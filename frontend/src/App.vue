<template>
  <el-container class="layout">
    <el-header class="header">IT 企业员工培训管理系统</el-header>
    <el-main>
      <el-menu mode="horizontal" router :default-active="$route.path" class="menu">
        <el-menu-item index="/course/course/list">课程</el-menu-item>
        <el-menu-item index="/training-plan/list">培训计划</el-menu-item>
        <el-menu-item index="/learning/my">学习</el-menu-item>
        <el-menu-item index="/exam/question">考试</el-menu-item>
        <el-menu-item index="/makeup/exam">补考</el-menu-item>
        <el-menu-item index="/statistics/index">统计</el-menu-item>
      </el-menu>
      <router-view />
    </el-main>
  </el-container>
</template>

<style scoped>
.layout {
  min-height: 100vh;
}
.header {
  color: #fff;
  background: #409eff;
  font-weight: 600;
  line-height: 60px;
}
.menu {
  margin-bottom: 16px;
}
</style>
