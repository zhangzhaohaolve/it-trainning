import http from './http';

export const getLecturerList = (params) => http.get('/course/lecturer/list', { params });
export const addLecturer = (data) => http.post('/course/lecturer', data);
export const updateLecturer = (data) => http.put('/course/lecturer', data);
export const deleteLecturer = (id) => http.delete(`/course/lecturer/${id}`);
