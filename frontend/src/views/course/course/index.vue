<template>
  <div>
    <el-form :inline="true" :model="queryForm" class="mb-12">
      <el-form-item label="课程名称"><el-input v-model="queryForm.name" clearable /></el-form-item>
      <el-form-item label="分类ID"><el-input v-model="queryForm.categoryId" clearable /></el-form-item>
      <el-form-item label="状态"><el-select v-model="queryForm.status" clearable style="width:120px"><el-option :value="0" label="草稿" /><el-option :value="1" label="上架" /></el-select></el-form-item>
      <el-form-item><el-button type="primary" @click="loadData">查询</el-button><el-button @click="resetQuery">重置</el-button><el-button type="success" @click="openAdd">新增</el-button></el-form-item>
    </el-form>
    <el-table :data="tableData" border>
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="name" label="课程名称" />
      <el-table-column prop="categoryId" label="分类ID" width="100" />
      <el-table-column prop="lecturerId" label="讲师ID" width="100" />
      <el-table-column prop="status" label="状态" width="100"><template #default="s">{{ s.row.status === 1 ? '上架' : '草稿' }}</template></el-table-column>
      <el-table-column label="操作" width="220"><template #default="s"><el-button type="primary" link @click="openEdit(s.row)">编辑</el-button><el-button type="danger" link @click="handleDelete(s.row.id)">删除</el-button></template></el-table-column>
    </el-table>
    <el-pagination class="mt-12" background layout="total, prev, pager, next, sizes" :total="total" v-model:current-page="queryForm.pageNum" v-model:page-size="queryForm.pageSize" @current-change="loadData" @size-change="loadData"/>
    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="600px">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="课程名称" prop="name"><el-input v-model="form.name" /></el-form-item>
        <el-form-item label="分类ID" prop="categoryId"><el-input v-model="form.categoryId" /></el-form-item>
        <el-form-item label="讲师ID" prop="lecturerId"><el-input v-model="form.lecturerId" /></el-form-item>
        <el-form-item label="封面" prop="coverUrl"><el-input v-model="form.coverUrl" /></el-form-item>
        <el-form-item label="状态" prop="status"><el-select v-model="form.status"><el-option :value="0" label="草稿" /><el-option :value="1" label="上架" /></el-select></el-form-item>
        <el-form-item label="描述" prop="description"><el-input type="textarea" v-model="form.description" /></el-form-item>
      </el-form>
      <template #footer><el-button @click="dialogVisible=false">取消</el-button><el-button type="primary" @click="submitForm">保存</el-button></template>
    </el-dialog>
  </div>
</template>
<script setup>
import { reactive, ref, onMounted } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import { getCoursePage, addCourse, updateCourse, deleteCourse } from '@/api/course';
const queryForm = reactive({ pageNum: 1, pageSize: 10, name: '', categoryId: '', status: undefined });
const tableData = ref([]); const total = ref(0); const dialogVisible = ref(false); const dialogTitle = ref('新增课程'); const formRef = ref();
const form = reactive({ id: null, name: '', categoryId: '', lecturerId: '', description: '', coverUrl: '', status: 0 });
const rules = { name: [{ required: true, message: '请输入课程名称', trigger: 'blur' }], categoryId: [{ required: true, message: '请输入分类ID', trigger: 'blur' }], lecturerId: [{ required: true, message: '请输入讲师ID', trigger: 'blur' }] };
const loadData = async () => { const { data } = await getCoursePage(queryForm); tableData.value = data.data.list; total.value = data.data.total; };
const resetQuery = () => { Object.assign(queryForm, { pageNum: 1, pageSize: 10, name: '', categoryId: '', status: undefined }); loadData(); };
const openAdd = () => { dialogTitle.value = '新增课程'; Object.assign(form, { id: null, name: '', categoryId: '', lecturerId: '', description: '', coverUrl: '', status: 0 }); dialogVisible.value = true; };
const openEdit = (row) => { dialogTitle.value = '编辑课程'; Object.assign(form, row); dialogVisible.value = true; };
const submitForm = async () => { await formRef.value.validate(); if (form.id) { await updateCourse(form); ElMessage.success('更新成功'); } else { await addCourse(form); ElMessage.success('新增成功'); } dialogVisible.value = false; loadData(); };
const handleDelete = async (id) => { await ElMessageBox.confirm('确认删除该课程吗？', '二次确认', { type: 'warning' }); await deleteCourse(id); ElMessage.success('删除成功'); loadData(); };
onMounted(loadData);
</script>
