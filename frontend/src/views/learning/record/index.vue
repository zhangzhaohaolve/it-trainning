<template>
  <div>
    <el-form :inline="true" :model="queryForm">
      <el-form-item label="部门ID"><el-input v-model="queryForm.deptId" /></el-form-item>
      <el-form-item label="用户ID"><el-input v-model="queryForm.userId" /></el-form-item>
      <el-form-item><el-button type="primary" @click="loadData">查询</el-button><el-button type="success" @click="openAdd">新增档案</el-button></el-form-item>
    </el-form>
    <el-table :data="tableData" border>
      <el-table-column prop="id" label="ID" width="80"/><el-table-column prop="userId" label="用户ID"/><el-table-column prop="courseId" label="课程ID"/><el-table-column prop="duration" label="学习时长(分钟)"/><el-table-column prop="finishTime" label="完成时间"/>
    </el-table>
    <el-pagination class="mt-12" background layout="total, prev, pager, next" :total="total" v-model:current-page="queryForm.pageNum" v-model:page-size="queryForm.pageSize" @current-change="loadData"/>
    <el-dialog v-model="dialogVisible" title="新增学习档案">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="用户ID" prop="userId"><el-input v-model="form.userId" /></el-form-item>
        <el-form-item label="课程ID" prop="courseId"><el-input v-model="form.courseId" /></el-form-item>
        <el-form-item label="学习时长" prop="duration"><el-input-number v-model="form.duration" :min="1" /></el-form-item>
      </el-form>
      <template #footer><el-button @click="dialogVisible=false">取消</el-button><el-button type="primary" @click="submitAdd">保存</el-button></template>
    </el-dialog>
  </div>
</template>
<script setup>
import { reactive, ref } from 'vue';
import { ElMessage } from 'element-plus';
import { getLearningRecordList, addLearningRecord } from '@/api/learningRecord';
const queryForm = reactive({ pageNum: 1, pageSize: 10, deptId: '', userId: '' });
const tableData = ref([]); const total = ref(0); const dialogVisible = ref(false); const formRef = ref();
const form = reactive({ userId: '', courseId: '', duration: 30 });
const rules = { userId: [{ required: true, message: '请输入用户ID', trigger: 'blur' }], courseId: [{ required: true, message: '请输入课程ID', trigger: 'blur' }], duration: [{ required: true, message: '请输入时长', trigger: 'change' }] };
const loadData = async () => { const { data } = await getLearningRecordList(queryForm); tableData.value = data.data.list; total.value = data.data.total; };
const openAdd = () => { Object.assign(form, { userId: '', courseId: '', duration: 30 }); dialogVisible.value = true; };
const submitAdd = async () => { await formRef.value.validate(); await addLearningRecord(form); ElMessage.success('新增成功'); dialogVisible.value = false; loadData(); };
</script>
