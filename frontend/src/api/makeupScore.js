import http from './http';

export const getMakeupScoreList = (params) => http.get('/makeup/score/list', { params });
export const submitMakeupScore = (data) => http.post('/makeup/score/add', data);
export const reviewMakeupScore = (data) => http.put('/makeup/score/update', data);
export const compareMakeupScore = (params) => http.get('/makeup/score/compare', { params });
