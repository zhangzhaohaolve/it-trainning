import http from './http';

export const getQuestionList = (params) => http.get('/exam/question/list', { params });
export const addQuestion = (data) => http.post('/exam/question/add', data);
export const updateQuestion = (data) => http.put('/exam/question/update', data);
export const deleteQuestion = (id) => http.delete(`/exam/question/delete/${id}`);
export const importQuestion = (data) => http.post('/exam/question/import', data);
