package com.practice.services;

import com.practice.dto.CourseDto;
import com.practice.dto.CreateCourseDto;
import com.practice.dto.StudentDto;

import java.util.List;

public interface CourseService {

    CourseDto createCourse(CreateCourseDto dto);

    List<StudentDto> getAllStudentsByCourseId(Long courseId);

    List<StudentDto> getActiveStudentsByCourseId(Long courseId);

}
