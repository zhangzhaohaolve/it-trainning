import http from './http';

export const getCategoryList = (params) => http.get('/course/category/list', { params });
export const addCategory = (data) => http.post('/course/category', data);
export const updateCategory = (data) => http.put('/course/category', data);
export const deleteCategory = (id) => http.delete(`/course/category/${id}`);
