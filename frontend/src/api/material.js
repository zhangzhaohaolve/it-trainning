import http from './http';

export const uploadMaterial = (formData) => http.post('/course/material/upload', formData, {
  headers: { 'Content-Type': 'multipart/form-data' }
});
export const getMaterialList = (courseId) => http.get('/course/material/list', { params: { courseId } });
export const deleteMaterial = (id) => http.delete(`/course/material/${id}`);
