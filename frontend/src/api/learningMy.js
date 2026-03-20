import http from './http';

export const getMyLearningList = (params) => http.get('/learning/my/list', { params });
