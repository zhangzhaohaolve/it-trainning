<template>
  <div>
    <el-form :inline="true" :model="query" class="mb-12">
      <el-form-item label="时间范围">
        <el-date-picker v-model="dateRange" type="daterange" start-placeholder="开始日期" end-placeholder="结束日期" value-format="YYYY-MM-DD" />
      </el-form-item>
      <el-form-item label="部门ID"><el-input v-model="query.deptId" clearable /></el-form-item>
      <el-form-item label="分类ID"><el-input v-model="query.categoryId" clearable /></el-form-item>
      <el-form-item><el-button type="primary" @click="loadAll">查询</el-button></el-form-item>
    </el-form>

    <el-row :gutter="16">
      <el-col :span="24" :md="8"><el-card><div ref="participateRef" style="height:320px"></div></el-card></el-col>
      <el-col :span="24" :md="8"><el-card><div ref="completionRef" style="height:320px"></div></el-card></el-col>
      <el-col :span="24" :md="8"><el-card><div ref="trendRef" style="height:320px"></div></el-card></el-col>
    </el-row>
  </div>
</template>

<script setup>
import * as echarts from 'echarts';
import { onMounted, onUnmounted, reactive, ref } from 'vue';
import { getParticipateStats, getCompletionStats, getPassRateStats } from '@/api/statistics';

const query = reactive({ startDate: '', endDate: '', deptId: '', categoryId: '' });
const dateRange = ref([]);
const participateRef = ref();
const completionRef = ref();
const trendRef = ref();
let chart1; let chart2; let chart3;

const loadAll = async () => {
  if (dateRange.value?.length === 2) {
    query.startDate = dateRange.value[0];
    query.endDate = dateRange.value[1];
  }
  const [p, c, t] = await Promise.all([
    getParticipateStats(query),
    getCompletionStats(query),
    getPassRateStats(query)
  ]);

  const pData = p.data.data || [];
  chart1.setOption({
    title: { text: '培训参与率' },
    tooltip: {},
    xAxis: { type: 'category', data: pData.map(i => i.deptName) },
    yAxis: { type: 'value' },
    series: [{ type: 'bar', data: pData.map(i => i.rate) }]
  });

  const cData = c.data.data || [];
  chart2.setOption({
    title: { text: '课程完成率' },
    tooltip: {},
    series: [{ type: 'pie', radius: '55%', data: cData.map(i => ({ name: i.courseName, value: i.rate })) }]
  });

  const tData = t.data.data || { dates: [], examPass: [], makeupPass: [] };
  chart3.setOption({
    title: { text: '考试/补考通过率趋势' },
    tooltip: { trigger: 'axis' },
    legend: { data: ['考试通过率', '补考通过率'] },
    xAxis: { type: 'category', data: tData.dates || [] },
    yAxis: { type: 'value' },
    series: [
      { name: '考试通过率', type: 'line', data: tData.examPass || [] },
      { name: '补考通过率', type: 'line', data: tData.makeupPass || [] }
    ]
  });
};

onMounted(() => {
  chart1 = echarts.init(participateRef.value);
  chart2 = echarts.init(completionRef.value);
  chart3 = echarts.init(trendRef.value);
  loadAll();
  window.addEventListener('resize', resizeAll);
});

const resizeAll = () => { chart1?.resize(); chart2?.resize(); chart3?.resize(); };
onUnmounted(() => window.removeEventListener('resize', resizeAll));
</script>
