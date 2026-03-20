<template><div>
  <el-form :inline="true" :model="query"><el-form-item label="考试ID"><el-input v-model="query.examId"/></el-form-item><el-form-item label="用户ID"><el-input v-model="query.userId"/></el-form-item><el-form-item><el-button type="primary" @click="load">查询</el-button><el-button @click="loadStats">统计</el-button><el-button type="success" @click="download">导出</el-button></el-form-item></el-form>
  <el-alert :title="`平均:${stats.avg ?? '-'} 最高:${stats.max ?? '-'} 最低:${stats.min ?? '-'}`" type="info" :closable="false" class="mb-12"/>
  <el-table :data="tableData" border><el-table-column prop="id" label="ID"/><el-table-column prop="examId" label="考试ID"/><el-table-column prop="userId" label="用户ID"/><el-table-column prop="score" label="成绩"/><el-table-column prop="status" label="状态"/></el-table>
  <el-pagination class="mt-12" background layout="total, prev, pager, next" :total="total" v-model:current-page="query.pageNum" v-model:page-size="query.pageSize" @current-change="load"/>
</div></template>
<script setup>
import { reactive, ref } from 'vue'; import { getScoreList, getScoreStats, exportScore } from '@/api/score';
const query=reactive({pageNum:1,pageSize:10,examId:'',userId:''}); const tableData=ref([]); const total=ref(0); const stats=reactive({avg:null,max:null,min:null});
const load=async()=>{const {data}=await getScoreList(query); tableData.value=data.data.list; total.value=data.data.total;};
const loadStats=async()=>{if(!query.examId) return; const {data}=await getScoreStats(query.examId); Object.assign(stats,data.data);};
const download=async()=>{if(!query.examId) return; const {data}=await exportScore(query.examId); const blob=new Blob([data]); const url=window.URL.createObjectURL(blob); const a=document.createElement('a'); a.href=url; a.download='exam-score.csv'; a.click(); window.URL.revokeObjectURL(url);};
</script>
