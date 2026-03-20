import http from './http';

export const getParticipateStats = (params) => http.get('/statistics/participate', { params });
export const getCompletionStats = (params) => http.get('/statistics/completion', { params });
export const getPassRateStats = (params) => http.get('/statistics/passrate', { params });
