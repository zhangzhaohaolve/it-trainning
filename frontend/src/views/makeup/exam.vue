<template><div>
  <el-form :inline="true" :model="query"><el-form-item label="用户ID"><el-input v-model="query.userId"/></el-form-item><el-form-item label="课程ID"><el-input v-model="query.courseId"/></el-form-item><el-form-item label="部门ID"><el-input v-model="query.deptId"/></el-form-item><el-form-item><el-button type="primary" @click="load">查询</el-button></el-form-item></el-form>
  <el-table :data="tableData" border><el-table-column prop="id" label="任务ID"/><el-table-column prop="examId" label="考试ID"/><el-table-column prop="userId" label="用户ID"/><el-table-column prop="reason" label="原因"/><el-table-column prop="status" label="状态"><template #default="s"><el-tag :type="s.row.status===1?'success':'danger'">{{ s.row.status===1?'已完成':'待补考' }}</el-tag></template></el-table-column></el-table>
  <el-pagination class="mt-12" background layout="total, prev, pager, next" :total="total" v-model:current-page="query.pageNum" v-model:page-size="query.pageSize" @current-change="load"/>
</div></template>
<script setup>
import { reactive, ref } from 'vue'; import { getMakeupExamList } from '@/api/makeupExam';
const query=reactive({pageNum:1,pageSize:10,userId:'',courseId:'',deptId:''}); const tableData=ref([]); const total=ref(0);
const load=async()=>{const {data}=await getMakeupExamList(query); tableData.value=data.data.list; total.value=data.data.total;};
</script>
