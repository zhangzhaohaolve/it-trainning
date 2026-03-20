<template><div>
  <el-form :inline="true" :model="query"><el-form-item label="补考任务ID"><el-input v-model="query.makeupId"/></el-form-item><el-form-item label="用户ID"><el-input v-model="query.userId"/></el-form-item><el-form-item><el-button type="primary" @click="load">查询</el-button></el-form-item></el-form>
  <el-table :data="tableData" border><el-table-column prop="id" label="ID"/><el-table-column prop="makeupId" label="补考任务ID"/><el-table-column prop="userId" label="用户ID"/><el-table-column prop="score" label="成绩"/><el-table-column prop="status" label="状态"/><el-table-column label="对比"><template #default="s"><span :style="{color:s.row.compareResult==='improve'?'green':'red'}">{{ s.row.compareResult==='improve'?'提升':'未提升' }}</span></template></el-table-column><el-table-column label="操作"><template #default="s"><el-button link type="primary" @click="review(s.row)">判卷</el-button><el-button link @click="compare(s.row)">对比原成绩</el-button></template></el-table-column></el-table>
  <el-pagination class="mt-12" background layout="total, prev, pager, next" :total="total" v-model:current-page="query.pageNum" v-model:page-size="query.pageSize" @current-change="load"/>
</div></template>
<script setup>
import { reactive, ref } from 'vue'; import { ElMessageBox, ElMessage } from 'element-plus'; import { getMakeupScoreList, reviewMakeupScore, compareMakeupScore } from '@/api/makeupScore';
const query=reactive({pageNum:1,pageSize:10,makeupId:'',userId:''}); const tableData=ref([]); const total=ref(0);
const load=async()=>{const {data}=await getMakeupScoreList(query); tableData.value=data.data.list.map(i=>({...i,compareResult:'unchanged'})); total.value=data.data.total;};
const review=async(row)=>{const { value } = await ElMessageBox.prompt('请输入判卷分数','判卷'); await reviewMakeupScore({id:row.id,score:Number(value),reviewer:1}); ElMessage.success('判卷完成'); load();};
const compare=async(row)=>{const {data}=await compareMakeupScore({makeupId:row.makeupId,userId:row.userId}); row.compareResult=data.data.compareResult; row.score=data.data.makeupScore;};
</script>
