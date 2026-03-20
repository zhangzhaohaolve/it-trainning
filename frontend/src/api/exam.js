import http from './http';

export const getExamList = (params) => http.get('/exam/exam/list', { params });
export const addExam = (data) => http.post('/exam/exam/add', data);
export const updateExam = (data) => http.put('/exam/exam/update', data);
export const deleteExam = (id) => http.delete(`/exam/exam/delete/${id}`);
export const publishExam = (id) => http.put(`/exam/exam/publish/${id}`);
export const autoGenerateExam = (data) => http.post('/exam/exam/auto-generate', data);
export const getExamDetail = (examId) => http.get(`/exam/exam/detail/${examId}`);
export const submitExam = (data) => http.post('/exam/exam/submit', data);
