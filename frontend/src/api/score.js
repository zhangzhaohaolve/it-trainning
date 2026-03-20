import http from './http';

export const getScoreList = (params) => http.get('/exam/score/list', { params });
export const getScoreStats = (examId) => http.get('/exam/score/stats', { params: { examId } });
export const exportScore = (examId) => http.get('/exam/score/export', { params: { examId }, responseType: 'blob' });
