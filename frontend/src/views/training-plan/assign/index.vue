<template>
  <div>
    <el-form :inline="true" :model="queryForm">
      <el-form-item label="计划ID"><el-input v-model="queryForm.planId" /></el-form-item>
      <el-form-item><el-button type="primary" @click="loadData">查询分配</el-button></el-form-item>
    </el-form>

    <el-card class="mb-12">
      <el-form :inline="true" :model="assignForm">
        <el-form-item label="计划ID"><el-input v-model="assignForm.planId" /></el-form-item>
        <el-form-item label="部门ID"><el-input v-model="assignForm.deptId" /></el-form-item>
        <el-form-item><el-button type="success" @click="assignDept">按部门分配</el-button></el-form-item>
      </el-form>
      <el-form :inline="true" :model="assignForm">
        <el-form-item label="用户多选">
          <el-select v-model="assignForm.userIds" multiple filterable style="width: 320px">
            <el-option v-for="u in userOptions" :key="u.value" :value="u.value" :label="u.label"/>
          </el-select>
        </el-form-item>
        <el-form-item><el-button type="primary" @click="assignBatchUsers">批量分配</el-button></el-form-item>
      </el-form>
    </el-card>

    <el-table :data="tableData" border>
      <el-table-column prop="id" label="ID" width="80" /><el-table-column prop="planId" label="计划ID" /><el-table-column prop="userId" label="用户ID" /><el-table-column prop="status" label="状态"><template #default="s">{{ s.row.status===1?'已完成':'未完成' }}</template></el-table-column>
      <el-table-column label="操作" width="120"><template #default="s"><el-button link type="danger" @click="handleDelete(s.row.id)">删除</el-button></template></el-table-column>
    </el-table>
  </div>
</template>
<script setup>
import { reactive, ref } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import { assignByBatch, assignByDept, getAssignList, deleteAssign } from '@/api/trainingAssign';
const queryForm = reactive({ planId: '' });
const assignForm = reactive({ planId: '', deptId: '', userIds: [] });
const tableData = ref([]);
const userOptions = ref([{ value: 1, label: '用户1' }, { value: 2, label: '用户2' }, { value: 3, label: '用户3' }]);
const loadData = async () => { if (!queryForm.planId) return ElMessage.warning('请输入计划ID'); const { data } = await getAssignList(queryForm.planId); tableData.value = data.data; };
const assignDept = async () => { if (!assignForm.planId || !assignForm.deptId) return ElMessage.warning('请填写计划ID和部门ID'); await assignByDept({ planId: assignForm.planId, deptId: assignForm.deptId }); ElMessage.success('分配成功'); };
const assignBatchUsers = async () => { if (!assignForm.planId || assignForm.userIds.length === 0) return ElMessage.warning('请填写计划ID并选择用户'); await assignByBatch({ planId: assignForm.planId, userIds: assignForm.userIds }); ElMessage.success('批量分配成功'); };
const handleDelete = async (id) => { await ElMessageBox.confirm('确认删除该分配记录吗？', '二次确认', { type: 'warning' }); await deleteAssign(id); ElMessage.success('删除成功'); loadData(); };
</script>
