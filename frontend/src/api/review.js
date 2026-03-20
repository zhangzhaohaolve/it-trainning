import http from './http';

export const getReviewList = (params) => http.get('/exam/review/list', { params });
export const reviewScore = (data) => http.put('/exam/review/update', data);
