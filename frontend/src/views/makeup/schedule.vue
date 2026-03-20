<template><div>
  <el-button type="success" @click="openAdd">新增安排</el-button>
  <el-calendar v-model="calendarDate" class="mt-12"/>
  <el-table :data="tableData" border class="mt-12"><el-table-column prop="id" label="ID"/><el-table-column prop="makeupId" label="补考任务ID"/><el-table-column prop="makeupTime" label="补考时间"/><el-table-column prop="location" label="地点"/><el-table-column label="操作"><template #default="s"><el-button link type="primary" @click="openEdit(s.row)">编辑</el-button></template></el-table-column></el-table>
  <el-dialog v-model="visible" :title="title"><el-form ref="formRef" :model="form" :rules="rules" label-width="100px"><el-form-item label="补考任务ID" prop="makeupId"><el-input v-model="form.makeupId" :disabled="!!form.id"/></el-form-item><el-form-item label="补考时间" prop="makeupTime"><el-date-picker v-model="form.makeupTime" type="datetime" value-format="YYYY-MM-DDTHH:mm:ss"/></el-form-item><el-form-item label="地点" prop="location"><el-input v-model="form.location"/></el-form-item></el-form><template #footer><el-button @click="visible=false">取消</el-button><el-button type="primary" @click="submit">保存</el-button></template></el-dialog>
</div></template>
<script setup>
import { ref, reactive } from 'vue'; import { ElMessage } from 'element-plus'; import { getMakeupScheduleList, addMakeupSchedule, updateMakeupSchedule } from '@/api/makeupSchedule';
const calendarDate=ref(new Date()); const tableData=ref([]); const visible=ref(false); const title=ref('新增安排'); const formRef=ref();
const form=reactive({id:null,makeupId:'',makeupTime:'',location:''}); const rules={makeupId:[{required:true,message:'必填',trigger:'blur'}],makeupTime:[{required:true,message:'必填',trigger:'change'}],location:[{required:true,message:'必填',trigger:'blur'}]};
const load=async()=>{const {data}=await getMakeupScheduleList(); tableData.value=data.data;}; load();
const openAdd=()=>{title.value='新增安排';Object.assign(form,{id:null,makeupId:'',makeupTime:'',location:''});visible.value=true;};
const openEdit=(r)=>{title.value='编辑安排';Object.assign(form,r);visible.value=true;};
const submit=async()=>{await formRef.value.validate(); if(form.id) await updateMakeupSchedule(form); else await addMakeupSchedule(form); ElMessage.success('保存成功'); visible.value=false; load();};
</script>
