<template>
  <div>
    <el-form :inline="true" :model="queryForm">
      <el-form-item label="开始时间"><el-date-picker v-model="queryForm.startTimeFrom" type="datetime" value-format="YYYY-MM-DDTHH:mm:ss" /></el-form-item>
      <el-form-item label="结束时间"><el-date-picker v-model="queryForm.startTimeTo" type="datetime" value-format="YYYY-MM-DDTHH:mm:ss" /></el-form-item>
      <el-form-item><el-button type="primary" @click="loadData">查询</el-button><el-button @click="resetQuery">重置</el-button><el-button type="success" @click="openAdd">新增</el-button></el-form-item>
    </el-form>
    <el-table :data="tableData" border>
      <el-table-column prop="id" label="ID" width="80" /><el-table-column prop="title" label="标题" /><el-table-column prop="startTime" label="开始时间" /><el-table-column prop="endTime" label="结束时间" />
      <el-table-column prop="status" label="状态" width="100"><template #default="s">{{ ['未开始','进行中','已结束'][s.row.status] }}</template></el-table-column>
      <el-table-column label="操作" width="220"><template #default="s"><el-button link type="primary" @click="openEdit(s.row)">编辑</el-button><el-button link type="danger" @click="handleDelete(s.row.id)">删除</el-button></template></el-table-column>
    </el-table>
    <el-pagination class="mt-12" background layout="total, prev, pager, next, sizes" :total="total" v-model:current-page="queryForm.pageNum" v-model:page-size="queryForm.pageSize" @current-change="loadData" @size-change="loadData"/>

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="600px">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="标题" prop="title"><el-input v-model="form.title" /></el-form-item>
        <el-form-item label="描述" prop="description"><el-input type="textarea" v-model="form.description" /></el-form-item>
        <el-form-item label="开始时间" prop="startTime"><el-date-picker v-model="form.startTime" type="datetime" value-format="YYYY-MM-DDTHH:mm:ss" /></el-form-item>
        <el-form-item label="结束时间" prop="endTime"><el-date-picker v-model="form.endTime" type="datetime" value-format="YYYY-MM-DDTHH:mm:ss" /></el-form-item>
      </el-form>
      <template #footer><el-button @click="dialogVisible=false">取消</el-button><el-button type="primary" @click="submitForm">保存</el-button></template>
    </el-dialog>
  </div>
</template>
<script setup>
import { reactive, ref, onMounted } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import { getTrainingPlanPage, addTrainingPlan, updateTrainingPlan, deleteTrainingPlan } from '@/api/trainingPlan';
const queryForm = reactive({ pageNum: 1, pageSize: 10, startTimeFrom: '', startTimeTo: '' });
const tableData = ref([]); const total = ref(0); const dialogVisible = ref(false); const dialogTitle = ref('新增培训计划'); const formRef = ref();
const form = reactive({ id: null, title: '', description: '', startTime: '', endTime: '' });
const rules = { title: [{ required: true, message: '请输入标题', trigger: 'blur' }], startTime: [{ required: true, message: '请选择开始时间', trigger: 'change' }], endTime: [{ required: true, message: '请选择结束时间', trigger: 'change' }] };
const loadData = async () => { const { data } = await getTrainingPlanPage(queryForm); tableData.value = data.data.list; total.value = data.data.total; };
const resetQuery = () => { Object.assign(queryForm, { pageNum: 1, pageSize: 10, startTimeFrom: '', startTimeTo: '' }); loadData(); };
const openAdd = () => { dialogTitle.value = '新增培训计划'; Object.assign(form, { id: null, title: '', description: '', startTime: '', endTime: '' }); dialogVisible.value = true; };
const openEdit = (row) => { dialogTitle.value = '编辑培训计划'; Object.assign(form, row); dialogVisible.value = true; };
const submitForm = async () => { await formRef.value.validate(); if (form.id) { await updateTrainingPlan(form); ElMessage.success('更新成功'); } else { await addTrainingPlan(form); ElMessage.success('新增成功'); } dialogVisible.value = false; loadData(); };
const handleDelete = async (id) => { await ElMessageBox.confirm('确认删除该培训计划吗？', '二次确认', { type: 'warning' }); await deleteTrainingPlan(id); ElMessage.success('删除成功'); loadData(); };
onMounted(loadData);
</script>
