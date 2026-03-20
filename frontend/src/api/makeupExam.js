import http from './http';

export const simulateMakeupGenerate = (params) => http.post('/makeup/exam/simulateAutoGenerate', null, { params });
export const getMakeupExamList = (params) => http.get('/makeup/exam/list', { params });
