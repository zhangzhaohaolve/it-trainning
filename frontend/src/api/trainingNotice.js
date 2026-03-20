import http from './http';

export const sendManualNotice = (data) => http.post('/training-plan/notice/manual-send', data);
export const getNoticeList = (params) => http.get('/training-plan/notice/list', { params });
export const updateNoticeReadStatus = (data) => http.put('/training-plan/notice/read-status', data);
