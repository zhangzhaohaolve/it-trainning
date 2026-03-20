import http from './http';

export const getLearningRecordList = (params) => http.get('/learning/record/list', { params });
export const addLearningRecord = (data) => http.post('/learning/record/add', data);
export const updateLearningRecord = (data) => http.put('/learning/record/update', data);
export const deleteLearningRecord = (id) => http.delete(`/learning/record/delete/${id}`);
