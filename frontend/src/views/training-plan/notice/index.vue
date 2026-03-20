<template>
  <div>
    <el-form :inline="true" :model="queryForm">
      <el-form-item label="计划ID"><el-input v-model="queryForm.planId" /></el-form-item>
      <el-form-item label="用户ID"><el-input v-model="queryForm.userId" /></el-form-item>
      <el-form-item><el-button type="primary" @click="loadData">查询</el-button><el-button type="success" @click="openSend">手动补发</el-button></el-form-item>
    </el-form>

    <el-table :data="tableData" border>
      <el-table-column prop="id" label="ID" width="80" /><el-table-column prop="planId" label="计划ID" /><el-table-column prop="userId" label="用户ID" /><el-table-column prop="type" label="类型"><template #default="s">{{ s.row.type===0?'系统':'手动' }}</template></el-table-column>
      <el-table-column prop="content" label="内容" />
      <el-table-column prop="status" label="阅读状态"><template #default="s">{{ s.row.status===1?'已读':'未读' }}</template></el-table-column>
      <el-table-column label="操作" width="120"><template #default="s"><el-button link type="primary" @click="markRead(s.row)">标记已读</el-button></template></el-table-column>
    </el-table>

    <el-dialog v-model="dialogVisible" title="手动补发通知">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="计划ID" prop="planId"><el-input v-model="form.planId" /></el-form-item>
        <el-form-item label="用户ID" prop="userId"><el-input v-model="form.userId" /></el-form-item>
        <el-form-item label="内容" prop="content"><el-input type="textarea" v-model="form.content" /></el-form-item>
      </el-form>
      <template #footer><el-button @click="dialogVisible=false">取消</el-button><el-button type="primary" @click="submitSend">发送</el-button></template>
    </el-dialog>
  </div>
</template>
<script setup>
import { reactive, ref } from 'vue';
import { ElMessage } from 'element-plus';
import { getNoticeList, sendManualNotice, updateNoticeReadStatus } from '@/api/trainingNotice';
const queryForm = reactive({ planId: '', userId: '' });
const tableData = ref([]); const dialogVisible = ref(false); const formRef = ref();
const form = reactive({ planId: '', userId: '', content: '' });
const rules = { planId: [{ required: true, message: '请输入计划ID', trigger: 'blur' }], userId: [{ required: true, message: '请输入用户ID', trigger: 'blur' }], content: [{ required: true, message: '请输入内容', trigger: 'blur' }] };
const loadData = async () => { const { data } = await getNoticeList(queryForm); tableData.value = data.data; };
const openSend = () => { Object.assign(form, { planId: '', userId: '', content: '' }); dialogVisible.value = true; };
const submitSend = async () => { await formRef.value.validate(); await sendManualNotice(form); ElMessage.success('发送成功'); dialogVisible.value = false; loadData(); };
const markRead = async (row) => { await updateNoticeReadStatus({ id: row.id, status: 1 }); ElMessage.success('设置已读成功'); loadData(); };
</script>
