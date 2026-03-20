import http from './http';

export const getMakeupScheduleList = () => http.get('/makeup/schedule/list');
export const addMakeupSchedule = (data) => http.post('/makeup/schedule/add', data);
export const updateMakeupSchedule = (data) => http.put('/makeup/schedule/update', data);
