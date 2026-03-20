import http from './http';

export const getLearningProgressList = (params) => http.get('/learning/progress/list', { params });
export const addLearningProgress = (data) => http.post('/learning/progress/add', data);
export const updateLearningProgress = (data) => http.put('/learning/progress/update', data);
export const deleteLearningProgress = (id) => http.delete(`/learning/progress/delete/${id}`);
export const getLearningProgressStats = (params) => http.get('/learning/progress/stats', { params });
