package com.practice.services;

import com.practice.dto.CreateGradeDto;
import com.practice.dto.GradeDto;

import java.util.List;
import java.util.Map;

public interface GradeService {

    GradeDto addGradeByStudentId(Long studentId, CreateGradeDto dto);

    List<GradeDto> getGradesByStudentId(Long studentId);

    List<GradeDto> getActiveCourseGradesByStudentId(Long studentId);

    List<GradeDto> getActiveCourseGradesByStudentIdAndCourseSemesterNumber(Long studentId, Long courseSemesterNumber);

    GradeDto updatePartialGradeByGradeId(Long gradeId, Map<String, Object> updates);

}
