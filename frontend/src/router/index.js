import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  { path: '/login', name: 'Login', component: () => import('@/views/system/user/LoginView.vue') },
  { path: '/', redirect: '/dashboard' },
  { path: '/dashboard', name: 'Dashboard', component: () => import('@/views/statistics/DashboardView.vue') },
  { path: '/course', name: 'Course', component: () => import('@/views/course/CourseListView.vue') },
  { path: '/plan', name: 'Plan', component: () => import('@/views/plan/PlanListView.vue') },
  { path: '/learning', name: 'Learning', component: () => import('@/views/learning/LearningTaskView.vue') },
  { path: '/exam', name: 'Exam', component: () => import('@/views/exam/ExamListView.vue') },
  { path: '/retake', name: 'Retake', component: () => import('@/views/retake/RetakeListView.vue') },
  { path: '/system/user', name: 'SystemUser', component: () => import('@/views/system/user/UserListView.vue') },
  { path: '/system/role', name: 'SystemRole', component: () => import('@/views/system/role/RoleListView.vue') },
  { path: '/system/dept', name: 'SystemDept', component: () => import('@/views/system/dept/DeptListView.vue') },
  { path: '/system/log', name: 'SystemLog', component: () => import('@/views/system/log/LogListView.vue') }
];

export default createRouter({
  history: createWebHistory(),
  routes
});
