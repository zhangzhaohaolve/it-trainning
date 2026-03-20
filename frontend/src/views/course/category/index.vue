<template>
  <div>
    <el-form :inline="true" :model="queryForm">
      <el-form-item label="分类名称"><el-input v-model="queryForm.name" clearable /></el-form-item>
      <el-form-item><el-button type="primary" @click="loadData">查询</el-button><el-button type="success" @click="openAdd">新增</el-button></el-form-item>
    </el-form>
    <el-table :data="tableData" border>
      <el-table-column prop="id" label="ID" width="80"/><el-table-column prop="name" label="名称"/><el-table-column prop="sort" label="排序" width="100"/>
      <el-table-column label="操作" width="180"><template #default="s"><el-button link type="primary" @click="openEdit(s.row)">编辑</el-button><el-button link type="danger" @click="handleDelete(s.row.id)">删除</el-button></template></el-table-column>
    </el-table>
    <el-dialog v-model="dialogVisible" :title="dialogTitle">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="名称" prop="name"><el-input v-model="form.name" /></el-form-item>
        <el-form-item label="排序" prop="sort"><el-input-number v-model="form.sort" :min="0" /></el-form-item>
      </el-form>
      <template #footer><el-button @click="dialogVisible=false">取消</el-button><el-button type="primary" @click="submitForm">保存</el-button></template>
    </el-dialog>
  </div>
</template>
<script setup>
import { reactive, ref, onMounted } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import { getCategoryList, addCategory, updateCategory, deleteCategory } from '@/api/category';
const queryForm = reactive({ name: '' }); const tableData = ref([]); const dialogVisible = ref(false); const dialogTitle = ref('新增分类'); const formRef = ref();
const form = reactive({ id: null, name: '', sort: 0 }); const rules = { name: [{ required: true, message: '请输入分类名', trigger: 'blur' }] };
const loadData = async () => { const { data } = await getCategoryList(queryForm); tableData.value = data.data; };
const openAdd = () => { dialogTitle.value = '新增分类'; Object.assign(form, { id: null, name: '', sort: 0 }); dialogVisible.value = true; };
const openEdit = (row) => { dialogTitle.value = '编辑分类'; Object.assign(form, row); dialogVisible.value = true; };
const submitForm = async () => { await formRef.value.validate(); if (form.id) { await updateCategory(form); ElMessage.success('更新成功'); } else { await addCategory(form); ElMessage.success('新增成功'); } dialogVisible.value = false; loadData(); };
const handleDelete = async (id) => { await ElMessageBox.confirm('确认删除该分类吗？', '二次确认', { type: 'warning' }); await deleteCategory(id); ElMessage.success('删除成功'); loadData(); };
onMounted(loadData);
</script>
