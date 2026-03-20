import http from './http';

export const getCoursePage = (params) => http.get('/course/page', { params });
export const getCourseDetail = (id) => http.get(`/course/${id}`);
export const addCourse = (data) => http.post('/course', data);
export const updateCourse = (data) => http.put('/course', data);
export const deleteCourse = (id) => http.delete(`/course/${id}`);
