<template>
  <div>
    <el-form :inline="true" :model="queryForm">
      <el-form-item label="用户ID"><el-input v-model="queryForm.userId" /></el-form-item>
      <el-form-item label="状态"><el-select v-model="queryForm.status" clearable style="width:140px"><el-option :value="0" label="未开始"/><el-option :value="1" label="学习中"/><el-option :value="2" label="已完成"/></el-select></el-form-item>
      <el-form-item><el-button type="primary" @click="loadData">查询</el-button></el-form-item>
    </el-form>
    <el-table :data="tableData" border>
      <el-table-column prop="planId" label="计划ID"/><el-table-column prop="courseId" label="课程ID"/><el-table-column prop="courseName" label="课程名称"/><el-table-column prop="progress" label="进度"/>
      <el-table-column prop="status" label="状态"><template #default="s"><el-tag :type="s.row.status===2?'success':(s.row.status===1?'warning':'info')">{{ ['未开始','学习中','已完成'][s.row.status] }}</el-tag></template></el-table-column>
      <el-table-column prop="deadline" label="截止时间"/>
      <el-table-column label="操作"><template #default="s"><el-button link type="primary" @click="goLearn(s.row)">去学习</el-button></template></el-table-column>
    </el-table>
  </div>
</template>
<script setup>
import { reactive, ref } from 'vue';
import { ElMessage } from 'element-plus';
import { getMyLearningList } from '@/api/learningMy';
const queryForm = reactive({ userId: '', status: undefined });
const tableData = ref([]);
const loadData = async () => { const { data } = await getMyLearningList(queryForm); tableData.value = data.data; };
const goLearn = (row) => ElMessage.info(`跳转课程 ${row.courseId} 学习页`);
</script>
