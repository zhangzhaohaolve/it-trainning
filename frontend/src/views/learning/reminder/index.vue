<template>
  <div>
    <el-form :inline="true" :model="queryForm">
      <el-form-item label="用户ID"><el-input v-model="queryForm.userId" /></el-form-item>
      <el-form-item label="提醒类型"><el-select v-model="queryForm.reminderType" clearable style="width:160px"><el-option :value="1" label="未学习"/><el-option :value="2" label="进度落后"/><el-option :value="3" label="临近截止"/></el-select></el-form-item>
      <el-form-item><el-button type="primary" @click="loadData">查询</el-button><el-button type="success" @click="openAdd">新增提醒</el-button></el-form-item>
    </el-form>
    <el-table :data="tableData" border>
      <el-table-column prop="id" label="ID" width="80"/><el-table-column prop="userId" label="用户ID"/><el-table-column prop="courseId" label="课程ID"/><el-table-column prop="reminderType" label="类型"/>
      <el-table-column prop="content" label="内容"/><el-table-column prop="status" label="状态"><template #default="s"><el-tag :type="s.row.status===1?'success':'warning'">{{ s.row.status===1?'已读':'未读' }}</el-tag></template></el-table-column>
      <el-table-column label="操作"><template #default="s"><el-button link type="primary" @click="markRead(s.row)">标记已读</el-button></template></el-table-column>
    </el-table>
    <el-pagination class="mt-12" background layout="total, prev, pager, next" :total="total" v-model:current-page="queryForm.pageNum" v-model:page-size="queryForm.pageSize" @current-change="loadData"/>
    <el-dialog v-model="dialogVisible" title="新增提醒">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="用户ID" prop="userId"><el-input v-model="form.userId" /></el-form-item>
        <el-form-item label="课程ID" prop="courseId"><el-input v-model="form.courseId" /></el-form-item>
        <el-form-item label="提醒类型" prop="reminderType"><el-select v-model="form.reminderType"><el-option :value="1" label="未学习"/><el-option :value="2" label="进度落后"/><el-option :value="3" label="临近截止"/></el-select></el-form-item>
        <el-form-item label="内容" prop="content"><el-input type="textarea" v-model="form.content" /></el-form-item>
      </el-form>
      <template #footer><el-button @click="dialogVisible=false">取消</el-button><el-button type="primary" @click="submitAdd">保存</el-button></template>
    </el-dialog>
  </div>
</template>
<script setup>
import { reactive, ref } from 'vue';
import { ElMessage } from 'element-plus';
import { getLearningReminderList, addLearningReminder, updateLearningReminder } from '@/api/learningReminder';
const queryForm = reactive({ pageNum: 1, pageSize: 10, userId: '', reminderType: undefined });
const tableData = ref([]); const total = ref(0); const dialogVisible = ref(false); const formRef = ref();
const form = reactive({ userId: '', courseId: '', reminderType: 1, content: '' });
const rules = { userId: [{ required: true, message: '请输入用户ID', trigger: 'blur' }], courseId: [{ required: true, message: '请输入课程ID', trigger: 'blur' }], content: [{ required: true, message: '请输入内容', trigger: 'blur' }] };
const loadData = async () => { const { data } = await getLearningReminderList(queryForm); tableData.value = data.data.list; total.value = data.data.total; };
const openAdd = () => { Object.assign(form, { userId: '', courseId: '', reminderType: 1, content: '' }); dialogVisible.value = true; };
const submitAdd = async () => { await formRef.value.validate(); await addLearningReminder(form); ElMessage.success('新增成功'); dialogVisible.value = false; loadData(); };
const markRead = async (row) => { await updateLearningReminder({ id: row.id, status: 1 }); ElMessage.success('设置成功'); loadData(); };
</script>
