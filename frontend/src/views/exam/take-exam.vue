<template><div>
  <el-form :inline="true" :model="query"><el-form-item label="考试ID"><el-input v-model="query.examId"/></el-form-item><el-form-item label="用户ID"><el-input v-model="query.userId"/></el-form-item><el-form-item><el-button type="primary" @click="startExam">开始考试</el-button></el-form-item></el-form>
  <el-alert v-if="seconds>0" :title="`剩余时间：${Math.floor(seconds/60)}分${seconds%60}秒`" type="warning" :closable="false" class="mb-12"/>
  <div v-for="(q,idx) in questions" :key="q.id" class="mb-12"><el-card><div>{{ idx+1 }}. 题目ID {{ q.questionId }}</div><el-input v-model="answers[q.questionId]" placeholder="请输入答案"/></el-card></div>
  <el-button type="success" @click="submit">提交考试</el-button>
</div></template>
<script setup>
import { reactive, ref, onUnmounted } from 'vue'; import { ElMessage } from 'element-plus'; import { getExamDetail, submitExam } from '@/api/exam';
const query=reactive({examId:'',userId:''}); const questions=ref([]); const answers=reactive({}); const seconds=ref(0); let timer=null;
const startExam=async()=>{const {data}=await getExamDetail(query.examId); questions.value=data.data; seconds.value=60*60; timer&&clearInterval(timer); timer=setInterval(()=>{seconds.value--; if(seconds.value<=0){clearInterval(timer); submit();}},1000);};
const submit=async()=>{const ans=JSON.stringify(answers); await submitExam({examId:Number(query.examId),userId:Number(query.userId),answers:ans}); ElMessage.success('提交成功'); timer&&clearInterval(timer);};
onUnmounted(()=>timer&&clearInterval(timer));
</script>
