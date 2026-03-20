<template>
  <div>
    <el-form :inline="true" :model="queryForm">
      <el-form-item label="部门ID"><el-input v-model="queryForm.deptId" /></el-form-item>
      <el-form-item label="用户ID"><el-input v-model="queryForm.userId" /></el-form-item>
      <el-form-item label="课程ID"><el-input v-model="queryForm.courseId" /></el-form-item>
      <el-form-item><el-button type="primary" @click="loadData">查询</el-button><el-button @click="loadStats">统计</el-button><el-button type="success" @click="openAdd">新增</el-button></el-form-item>
    </el-form>
    <el-alert :title="`课程平均进度:${stats.courseAvg ?? '-'}，部门平均进度:${stats.deptAvg ?? '-'}`" type="info" :closable="false" class="mb-12"/>
    <el-table :data="tableData" border>
      <el-table-column prop="id" label="ID" width="80"/><el-table-column prop="userId" label="用户ID"/><el-table-column prop="courseId" label="课程ID"/><el-table-column prop="progress" label="进度"/>
      <el-table-column label="操作"><template #default="s"><el-button link type="primary" @click="openEdit(s.row)">编辑</el-button><el-button link type="danger" @click="handleDelete(s.row.id)">删除</el-button></template></el-table-column>
    </el-table>
    <el-pagination class="mt-12" background layout="total, prev, pager, next" :total="total" v-model:current-page="queryForm.pageNum" v-model:page-size="queryForm.pageSize" @current-change="loadData"/>
    <el-dialog v-model="dialogVisible" :title="dialogTitle">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户ID" prop="userId"><el-input v-model="form.userId" :disabled="!!form.id" /></el-form-item>
        <el-form-item label="课程ID" prop="courseId"><el-input v-model="form.courseId" :disabled="!!form.id" /></el-form-item>
        <el-form-item label="进度" prop="progress"><el-input-number v-model="form.progress" :min="0" :max="100" /></el-form-item>
      </el-form>
      <template #footer><el-button @click="dialogVisible=false">取消</el-button><el-button type="primary" @click="submitForm">保存</el-button></template>
    </el-dialog>
  </div>
</template>
<script setup>
import { reactive, ref } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import { getLearningProgressList, addLearningProgress, updateLearningProgress, deleteLearningProgress, getLearningProgressStats } from '@/api/learningProgress';
const queryForm = reactive({ pageNum: 1, pageSize: 10, deptId: '', userId: '', courseId: '' });
const tableData = ref([]); const total = ref(0); const stats = reactive({ courseAvg: null, deptAvg: null });
const dialogVisible = ref(false); const dialogTitle = ref('新增进度'); const formRef = ref();
const form = reactive({ id: null, userId: '', courseId: '', progress: 0 });
const rules = { userId: [{ required: true, message: '请输入用户ID', trigger: 'blur' }], courseId: [{ required: true, message: '请输入课程ID', trigger: 'blur' }], progress: [{ required: true, message: '请输入进度', trigger: 'change' }] };
const loadData = async () => { const { data } = await getLearningProgressList(queryForm); tableData.value = data.data.list; total.value = data.data.total; };
const loadStats = async () => { const { data } = await getLearningProgressStats({ courseId: queryForm.courseId || undefined, deptId: queryForm.deptId || undefined }); Object.assign(stats, data.data); };
const openAdd = () => { dialogTitle.value = '新增进度'; Object.assign(form, { id: null, userId: '', courseId: '', progress: 0 }); dialogVisible.value = true; };
const openEdit = (row) => { dialogTitle.value = '编辑进度'; Object.assign(form, row); dialogVisible.value = true; };
const submitForm = async () => { await formRef.value.validate(); if (form.id) await updateLearningProgress({ id: form.id, progress: form.progress }); else await addLearningProgress(form); ElMessage.success('保存成功'); dialogVisible.value = false; loadData(); };
const handleDelete = async (id) => { await ElMessageBox.confirm('确认删除该记录吗？', '二次确认', { type: 'warning' }); await deleteLearningProgress(id); ElMessage.success('删除成功'); loadData(); };
</script>
