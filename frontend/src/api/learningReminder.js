import http from './http';

export const getLearningReminderList = (params) => http.get('/learning/reminder/list', { params });
export const addLearningReminder = (data) => http.post('/learning/reminder/add', data);
export const updateLearningReminder = (data) => http.put('/learning/reminder/update', data);
export const deleteLearningReminder = (id) => http.delete(`/learning/reminder/delete/${id}`);
