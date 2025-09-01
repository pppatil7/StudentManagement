package com.practice.services;

import com.practice.dto.ApiResponse;
import com.practice.dto.CreateStudentDto;
import com.practice.dto.EnrollmentDto;
import com.practice.dto.StudentDto;

import java.util.List;
import java.util.Map;

public interface StudentService {

    StudentDto createStudent(CreateStudentDto dto);

    StudentDto updatePartialStudent(Long studentId, Map<String, Object> updates);

    StudentDto getStudentByStudentId(Long studentId);

    List<StudentDto> getAllStudents();

    List<EnrollmentDto> getEnrollmentsByStudentId(Long studentId);

    List<StudentDto> getAllStudentsByCourseId(Long courseId);

    List<StudentDto> getActiveStudentsByCourseId(Long courseId);

    ApiResponse deleteStudentByStudentId(Long studentId);
}
