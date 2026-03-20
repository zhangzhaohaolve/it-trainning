import http from './http';

export const getTrainingPlanPage = (params) => http.get('/training-plan/page', { params });
export const getTrainingPlanDetail = (id) => http.get(`/training-plan/${id}`);
export const addTrainingPlan = (data) => http.post('/training-plan', data);
export const updateTrainingPlan = (data) => http.put('/training-plan', data);
export const deleteTrainingPlan = (id) => http.delete(`/training-plan/${id}`);
