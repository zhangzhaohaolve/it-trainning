<template>
  <div>
    <el-form :inline="true" :model="queryForm">
      <el-form-item label="课程ID"><el-input v-model="queryForm.courseId" /></el-form-item>
      <el-form-item><el-button type="primary" @click="loadData">查询</el-button></el-form-item>
    </el-form>
    <el-upload :auto-upload="false" :on-change="onFileChange" :limit="1"><el-button type="success">选择文件</el-button></el-upload>
    <el-button class="mt-12" type="primary" @click="doUpload">上传课件</el-button>
    <el-table :data="tableData" border class="mt-12">
      <el-table-column prop="id" label="ID" width="80"/><el-table-column prop="fileName" label="文件名"/><el-table-column prop="fileType" label="类型" width="100"/><el-table-column prop="size" label="大小" width="120"/>
      <el-table-column label="操作" width="120"><template #default="s"><el-button link type="danger" @click="handleDelete(s.row.id)">删除</el-button></template></el-table-column>
    </el-table>
  </div>
</template>
<script setup>
import { reactive, ref } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import { uploadMaterial, getMaterialList, deleteMaterial } from '@/api/material';
const queryForm = reactive({ courseId: '' }); const tableData = ref([]); const currentFile = ref(null);
const onFileChange = (file) => { currentFile.value = file.raw; };
const loadData = async () => { if (!queryForm.courseId) return; const { data } = await getMaterialList(queryForm.courseId); tableData.value = data.data; };
const doUpload = async () => {
  if (!queryForm.courseId) return ElMessage.warning('请先输入课程ID');
  if (!currentFile.value) return ElMessage.warning('请选择文件');
  const formData = new FormData(); formData.append('courseId', queryForm.courseId); formData.append('file', currentFile.value);
  await uploadMaterial(formData); ElMessage.success('上传成功'); loadData();
};
const handleDelete = async (id) => { await ElMessageBox.confirm('确认删除该课件吗？', '二次确认', { type: 'warning' }); await deleteMaterial(id); ElMessage.success('删除成功'); loadData(); };
</script>
