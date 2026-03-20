package com.company.training.modules.course.service.impl;

import com.company.training.common.exception.BizException;
import com.company.training.modules.course.dto.LecturerAddDTO;
import com.company.training.modules.course.dto.LecturerQueryDTO;
import com.company.training.modules.course.dto.LecturerUpdateDTO;
import com.company.training.modules.course.entity.Lecturer;
import com.company.training.modules.course.mapper.LecturerMapper;
import com.company.training.modules.course.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LecturerServiceImpl implements LecturerService {
    private final LecturerMapper lecturerMapper;

    @Override
    public List<Lecturer> list(LecturerQueryDTO queryDTO) {
        return lecturerMapper.selectList(queryDTO);
    }

    @Override
    public Long add(LecturerAddDTO dto) {
        Lecturer entity = new Lecturer();
        entity.setName(dto.getName());
        entity.setTitle(dto.getTitle());
        entity.setAvatarUrl(dto.getAvatarUrl());
        entity.setBio(dto.getBio());
        lecturerMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void update(LecturerUpdateDTO dto) {
        Lecturer entity = new Lecturer();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setTitle(dto.getTitle());
        entity.setAvatarUrl(dto.getAvatarUrl());
        entity.setBio(dto.getBio());
        if (lecturerMapper.updateById(entity) == 0) {
            throw new BizException("讲师不存在或已删除");
        }
    }

    @Override
    public void delete(Long id) {
        if (lecturerMapper.logicDeleteById(id) == 0) {
            throw new BizException("讲师不存在或已删除");
        }
    }
}
