<template><div>
  <el-form :inline="true" :model="query"><el-form-item label="考试ID"><el-input v-model="query.examId"/></el-form-item><el-form-item><el-button type="primary" @click="load">查询</el-button></el-form-item></el-form>
  <el-table :data="tableData" border><el-table-column prop="id" label="成绩ID"/><el-table-column prop="userId" label="用户ID"/><el-table-column prop="score" label="当前分数"/><el-table-column label="操作"><template #default="s"><el-button link type="primary" @click="openReview(s.row)">批改简答题</el-button></template></el-table-column></el-table>
  <el-dialog v-model="visible" title="人工判卷"><el-form ref="formRef" :model="form" :rules="rules" label-width="90px"><el-form-item label="成绩ID" prop="scoreId"><el-input v-model="form.scoreId" disabled/></el-form-item><el-form-item label="人工得分" prop="manualScore"><el-input-number v-model="form.manualScore" :min="0"/></el-form-item><el-form-item label="阅卷人" prop="reviewer"><el-input v-model="form.reviewer"/></el-form-item></el-form><template #footer><el-button @click="visible=false">取消</el-button><el-button type="primary" @click="submit">保存</el-button></template></el-dialog>
</div></template>
<script setup>
import { reactive, ref } from 'vue'; import { ElMessage } from 'element-plus'; import { getScoreList } from '@/api/score'; import { reviewScore } from '@/api/review';
const query=reactive({pageNum:1,pageSize:20,examId:'',userId:''}); const tableData=ref([]); const visible=ref(false); const formRef=ref(); const form=reactive({scoreId:'',manualScore:0,reviewer:''});
const rules={manualScore:[{required:true,message:'必填',trigger:'change'}],reviewer:[{required:true,message:'必填',trigger:'blur'}]};
const load=async()=>{const {data}=await getScoreList(query); tableData.value=data.data.list;};
const openReview=(row)=>{Object.assign(form,{scoreId:row.id,manualScore:row.score,reviewer:''}); visible.value=true;};
const submit=async()=>{await formRef.value.validate(); await reviewScore({scoreId:Number(form.scoreId),manualScore:form.manualScore,reviewer:Number(form.reviewer)}); ElMessage.success('批改成功'); visible.value=false; load();};
</script>
