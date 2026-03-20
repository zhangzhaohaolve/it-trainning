import http from './http';

export const assignByBatch = (data) => http.post('/training-plan/assign/batch', data);
export const assignBySingle = (data) => http.post('/training-plan/assign/single', data);
export const assignByDept = (data) => http.post('/training-plan/assign/dept', data);
export const getAssignList = (planId) => http.get(`/training-plan/assign/list/${planId}`);
export const deleteAssign = (id) => http.delete(`/training-plan/assign/${id}`);
