package com.company.training.modules.course.service;

import com.company.training.modules.course.dto.LecturerAddDTO;
import com.company.training.modules.course.dto.LecturerQueryDTO;
import com.company.training.modules.course.dto.LecturerUpdateDTO;
import com.company.training.modules.course.entity.Lecturer;

import java.util.List;

public interface LecturerService {
    List<Lecturer> list(LecturerQueryDTO queryDTO);

    Long add(LecturerAddDTO dto);

    void update(LecturerUpdateDTO dto);

    void delete(Long id);
}
