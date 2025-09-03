package com.practice.services;

import com.practice.dto.CreateGradeDto;
import com.practice.dto.GradeDto;

import java.util.List;

public interface GradeService {

    GradeDto addGradeByStudentId(Long studentId, CreateGradeDto dto);

    List<GradeDto> getGradesByStudentId(Long studentId);

    List<GradeDto> getActiveCourseGradesByStudentId(Long studentId);

}
